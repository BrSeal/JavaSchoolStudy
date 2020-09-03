import React, {Component} from 'react'

export class AssignDriversButton extends Component{
    render() {
        const doSmth=function(){
            alert('AssignDriversButton pressed!!!');
        }

        return (
            <button className={'btn btn-secondary btn-sm'} onClick={doSmth}>Assign drivers</button>
        );
    }
}