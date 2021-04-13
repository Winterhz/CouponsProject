import axios from "axios";
import { ChangeEvent, Component } from "react";
import { UserDetails } from "../../models/UserDetails";
import { ActionType } from "../redux/ActionType";
import { store } from "../redux/store";

import "./UpdateProfile.css";

interface IUpdateProfileState {
    newName: string;
    newPassword: string;
}

export default class UpdateProfile extends Component<any, IUpdateProfileState>{

    constructor(props: any) {
        super(props);
        this.state = {
            newName: "",
            newPassword: ""
        }
    }

    public setNewName = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ newName: text });
    }

    public setNewPassword = (event: ChangeEvent<HTMLInputElement>) => {
        let newPassword = event.target.value;
        console.log(newPassword);
        this.setState({ newPassword });
    }

    public async updateProfile() {
        if (this.state.newName.length < 3) {
            alert("user name length should be more then 3 symbols!");
            return;
        } else if (this.state.newPassword.length < 6) {
            alert("password length should be more then 6 symbols!");
            return;
        } else {
            let userDetails = new UserDetails(store.getState().userDetails.userType, store.getState().userDetails.id, this.state.newName, store.getState().userDetails.companyName, this.state.newPassword)
            await axios.put("http://localhost:8080/users/" + userDetails);
            store.dispatch({ type: ActionType.getUserDetails, payload: userDetails });
            alert("Your profile is updated!");
        }

    }

    public render() {
        return (
            <div className="updateProfileUserDetails">
                <p>Old name: {store.getState().userDetails.userName}</p>
                <p>New name: <input type="text" className="inputUserDetailsToUpdate" placeholder="enter new name" onChange={this.setNewName} /></p>
                <p>New password: <input type="text" className="inputUserDetailsToUpdate" placeholder="enter new password" onChange={this.setNewPassword} /></p>
                <input type="button" className="updateProfile" value="update profile" onClick={this.updateProfile} />
            </div>
        );
    }

}