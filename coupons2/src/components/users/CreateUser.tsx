import axios from "axios";
import { ChangeEvent, Component } from "react";
import { UserDetails } from "../../models/UserDetails";
import "./CreateUser.css";

interface ICreateUserState {
    userType: string;
    userName: string;
    companyName: string;
    password: string;
}

export default class CreateUser extends Component<any, ICreateUserState>{

    constructor(props: any) {
        super(props);
        this.state = {
            userType: "",
            userName: "",
            companyName: "",
            password: ""
        }
    }

    public setUserName = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ userName: text });
    }

    public setPassword = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ password: text });
    }

    public setUserType = (event: ChangeEvent<HTMLSelectElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ userType: text });
    }

    public setCompanyName = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ companyName: text });
    }

    public createUser = async () => {
        try {
            let user = new UserDetails(this.state.userType, null, this.state.userName,
                this.state.companyName, this.state.password);
            await axios.post("http://localhost:8080/users/", user);
            alert("User created!");

        } catch (err) {
            alert(err.message);
            console.log(err);
        }
    }

    public render() {
        return (
            <div className="createUserDiv">
                <span>User name: <input className="createUserInput" type="text" onChange={this.setUserName} /><br /><br /></span>
                <span>Password: <input className="createUserInput" type="text" onChange={this.setPassword} /><br /><br /></span>
                <span>UserType: </span>
                <select className="createUserSelect" onChange={this.setUserType} >
                    <option className="userTypeOptionInCreate">ADMIN</option>
                    <option className="userTypeOptionInCreate">COMPANY</option>
                    <option className="userTypeOptionInCreate">CUSTOMER</option>
                </select> <br /><br />
                {this.state.userType==="COMPANY" && <span>Company name: <input className="createUserInput" type="text" onChange={this.setCompanyName} /><br /><br /></span>}
                <input className="createUserButtonInCreate" type="button" value="Create user" onClick={this.createUser} />
            </div>
        );
    }
}