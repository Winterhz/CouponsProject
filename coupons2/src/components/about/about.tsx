import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { Component } from 'react';
import "./about.css"

interface CustomerState {

}

export default class About extends Component<any, CustomerState>{

    constructor(props: any) {
        super(props);
        this.state = {

        };
    }

    public render() {
        return (
            <div className="about">
                <h1>Third phase of project coupons as part of practical learning in john bryce college.</h1>
                <h2>Made by student Kostya Kryhtin.</h2>
                <h3>Hope you like it and thanks for cheking it out!</h3>
                <FontAwesomeIcon icon={["far", "smile"]} className="iconSmile" size="2x" />
            </div>
        );
    }
}