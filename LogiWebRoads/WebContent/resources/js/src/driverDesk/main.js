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
            onClick={ () => ReactDOM.render(e(ChangeDriverStatusForm), details)}
            >
            Change status
        </button>
        );
    }
}

class ChangeDriverStatusForm extends React.Component {
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

class ChangeCargoStatusForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            id: document.body.dataset.cargoid,
            status: document.body.dataset.cargostatus
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
            id:Number(this.state.id),
            status:Number(this.state.status)
        };

        $.ajax({
            method: 'POST',
            url: '../cargo/updateStatus/',
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function () {
                alert('Waypoint done!');
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
                    <option value={"0"}>Prepared</option>
                    <option value={"1"}>Loaded</option>
                    <option value={"2"}>Delivered</option>
                </select>
                <input className={"btn btn-secondary"} type={"submit"} name={"Finish waypoint"}/>
            </form>
        );
    }


}

const details = document.getElementById('details');
const cargoStatusForm = document.getElementById('cargoStatusForm');
ReactDOM.render(e(ChangeStatusButton), details);
ReactDOM.render(e(ChangeCargoStatusForm), cargoStatusForm);