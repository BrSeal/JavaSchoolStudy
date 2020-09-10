'use strict';

const e = React.createElement;
let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");
const baseUrl = document.body.dataset.url;

class ChangeStatusButton extends React.Component {
    render() {
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });

        return(
            <button
            className={"btn btn-secondary"}
            onClick={ () => ReactDOM.render(e(ChangeStatusForm), domContainer)}
            >
            Change status
        </button>
        );
    }
}

class ChangeStatusForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
                status: document.body.dataset.status
            }
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    onChange(e) {
        this.setState({
            status: Number(e.target.value)
        })
    }

    onSubmit(e) {
        e.preventDefault();
        let data = {
            status:this.state.status
        };

        $.ajax({
            method: 'POST',
            url: '../driver/updateStatus/',
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                alert('Status updated!');
            },
            error: function (response) {
                alert(response.responseText);
            }
        });
    }

    render() {
        return (
            <form onSubmit={this.onSubmit} method={"post"}>
                <div><b>Status:</b></div>
                <select
                    className={"form-control"}
                    name={"status"}
                        onChange={this.onChange}
                        defaultValue={this.state.status}>
                    <option value={"0"}>Free</option>
                    <option value={"1"}>Rest</option>
                    <option value={"2"}>Driving</option>
                </select>
                <input className={"btn btn-secondary"} type={"submit"} name={"Confirm"}/>
            </form>
        );
    }


}

const domContainer = document.getElementById('details');
ReactDOM.render(e(ChangeStatusButton), domContainer);