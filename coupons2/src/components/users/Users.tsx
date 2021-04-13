import axios from "axios";
import { ChangeEvent, Component } from "react";
import { Unsubscribe } from "redux";
import { UserDetails } from "../../models/UserDetails";
import { ActionType } from "../redux/ActionType";
import { store } from "../redux/store";

import "./Users.css";

interface IUsersState {
    users: UserDetails[];
    filterById: number;
    filterByName: string;
    filterByCompanyName: string;
}

export default class Coupons extends Component<any, IUsersState>{

    private unsubscribeStore: Unsubscribe;

    constructor(props: any) {
        super(props);
        this.state = {
            users: store.getState().users,
            filterById: 0,
            filterByName: "",
            filterByCompanyName: ""
        }
        this.unsubscribeStore = store.subscribe(() => this.setState(
            {
                users: store.getState().users
            }
        )
        )
    }

    public async componentDidMount() {
        const response = await axios.get<UserDetails[]>("http://localhost:8080/users");
        console.log(response);

        store.dispatch({ type: ActionType.getUsers, payload: response.data });
    }

    public componentWillUnmount() {
        this.unsubscribeStore();
    }

    public goToUserCreation = () => {
        this.props.history.push("/createUser");
    }

    public byId = (event: ChangeEvent<HTMLInputElement>) => {
        let id = +event.target.value;
        console.log(id);
        this.setState({ filterById: id });
    }
    public byName = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ filterByName: text });
    }
    public byCompanyName = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ filterByCompanyName: text });
    }

    public deleteUser = async (id: number) => {
        await axios.delete("http://localhost:8080/users/" + id);
        store.dispatch({ type: ActionType.deleteUser, payload: id });
        alert("User is deleted!");
    }

    public render() {
        return (
            <div className="users">
                <span>Search by Name:</span> <input className="searchUserInUsers" type="text" onChange={this.byName} />
                <span>Search by Company Name:</span> <input className="searchUserInUsers" type="text" onChange={this.byCompanyName} /><br />
                <span>Search by id:</span> <input className="searchUserInUsers" type="number" onChange={this.byId} />
                {sessionStorage.getItem("userType") === "ADMIN" && <input className="createNewUserForAdmin" type="button" value="Create new user" onClick={this.goToUserCreation} />}
                <br /><br />
                <div className="usersTable">
                    <h1>All Users: </h1>
                    <table className="usersInfo">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Username</th>
                                <th>User type</th>
                                <th>Company name</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.users.filter(user => {
                                    if (this.state.filterById === 0) {
                                        return true;
                                    }
                                    
                                    return user.id === this.state.filterById;
                                }).filter(user => {
                                    if (this.state.filterByName === "") {
                                        return true;
                                    }

                                    return user.userName.toLowerCase().includes(this.state.filterByName.toLowerCase());
                                }).filter(user => {
                                    if (this.state.filterByCompanyName === "") {
                                        return true;
                                    } else if (user.userType === "COMPANY") {

                                        return user.companyName.toLowerCase().includes(this.state.filterByCompanyName.toLowerCase());
                                    }
                                }).map(user => (
                                    <tr key={user.id}>
                                        <td className="userRow">{user.id}</td>
                                        <td className="userRow">{user.userName}</td>
                                        <td className="userRow">{user.userType}</td>
                                        <td className="userRow">{user.companyName}</td>
                                        <td className="userRow" id="deleteUserForAdmin" onClick={() => this.deleteUser(user.id)}>delete user</td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }

}