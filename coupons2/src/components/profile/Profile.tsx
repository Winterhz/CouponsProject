import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import { Unsubscribe } from "redux";
import { UserDetails } from "../../models/UserDetails";
import { store } from "../redux/store";

import "./Profile.css";

interface IProfileState {
    userDetails: UserDetails
}

export default class Profile extends Component<any, IProfileState>{

    private unsubscribeStore: Unsubscribe;

    constructor(props: any) {
        super(props);
        this.state = {
            userDetails: store.getState().userDetails
        }
        this.unsubscribeStore = store.subscribe(() => this.setState(
            {
                userDetails: store.getState().userDetails
            }
        )
        )
    }

    componentWillUnmount(){
        this.unsubscribeStore();
    }

    public render() {
        return (
            <div className="profileUserDetails">
                <p>My name: {this.state.userDetails.userName}</p>
                {this.state.userDetails.userType==="COMPANY" && <p>My Company: {this.state.userDetails.companyName}</p>}
                <NavLink to="/updateProfile" exact><input className="updateProfile" type="button" value="update profile"/></NavLink>
            </div>
        );
    }

}