import React, { Component, ChangeEvent } from 'react'

import axios from "axios";
import "./Login.css";
import { UserLoginData } from '../../models/UserLoginData';
import { SuccessfulLoginServerResponse } from '../../models/SuccessfulLoginServerResponse';
import { store } from '../redux/store';
import { ActionType } from '../redux/ActionType';
import { UserDetails } from '../../models/UserDetails';

interface LoginState {
    userName: string;
    password: string;
}

export default class Login extends Component<any, LoginState>{

    public constructor(props: any) {
        super(props);
        this.state = {
            userName: "",
            password: ""
        };
    }

    private setUserName = (args: ChangeEvent<HTMLInputElement>) => {
        const userName = args.target.value;
        this.setState({ userName });
    }

    private setPassword = (args: ChangeEvent<HTMLInputElement>) => {
        const password = args.target.value;
        this.setState({ password });
    }

    private login = async () => {

        // fetch("http://localhost:3002/products")
        //     .then(response => response.json())
        //     .then(products => this.setState({ products }))
        //     .catch(err => alert(err.message));

        try {
            let userLoginData = new UserLoginData(this.state.userName, this.state.password);
            const response = await axios.post<SuccessfulLoginServerResponse>("http://localhost:8080/users/login", userLoginData);
            const serverResponse = response.data;
            axios.defaults.headers.common['Authorization'] = serverResponse.token;
            sessionStorage.setItem("userType", serverResponse.userType);
            let tokenStr ="" + serverResponse.token;
            sessionStorage.setItem("token", tokenStr);
            let userDetails = new UserDetails(serverResponse.userType, serverResponse.id, serverResponse.userName, serverResponse.companyName, serverResponse.password);
            store.dispatch({ type: ActionType.Login, payload: userDetails });
            console.log(userDetails);
            this.props.history.push("/coupons");
        }
        catch (err) {
            alert(err.message);
            console.log(err);
            //console.log(JSON.stringify(err));
        }
    }

    private registration = async () => {
        try {
            let userLoginData = new UserLoginData(this.state.userName, this.state.password);
            await axios.post<SuccessfulLoginServerResponse>("http://localhost:8080/users", userLoginData);
        } catch (err) {
            alert(err.message);
            console.log(err);
        }
        this.login();
    }

    public render() {
        return (
            <div className="login">
                <input type="text" placeholder="User name" name="username"
                    value={this.state.userName} onChange={this.setUserName} /><br />
                <input type="password" placeholder="Password" name="password"
                    value={this.state.password} onChange={this.setPassword} /><br />
                <input type="button" id="login" value="login" onClick={this.login} />
                <input type="button" id="reg" value="registration" onClick={this.registration} />
            </div>
        );
    }
}

