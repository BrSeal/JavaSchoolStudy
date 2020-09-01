import React, {Component} from 'react'

export class AssignDriversButton extends Component{
    render() {
        const doSmth=function(){
            alert('AssignDriversButton pressed!!!');
        }

        return (
            <button onClick={doSmth}>Assign drivers</button>
        );
    }
}