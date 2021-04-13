import axios from "axios";
import { ChangeEvent, Component } from "react";
import { Company } from "../../models/Company";
import "./UpdateCompany.css";

interface IUpdateUserState {
    id: number;
    name:string;
    address:string;
    phoneNumber:number;
}

export default class CreateUser extends Component<any, IUpdateUserState>{

    constructor(props: any) {
        super(props);
        this.state = {
            id: 0,
            name: "",
            address: "",
            phoneNumber: 0
        }
    }

    public setId = (event: ChangeEvent<HTMLInputElement>) => {
        let num = +event.target.value;
        console.log(num);
        this.setState({ id: num });
    }

    public setName = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ name: text });
    }

    public setAddress = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ address: text });
    }

    public setPhoneNumber = (event: ChangeEvent<HTMLInputElement>) => {
        let num = +event.target.value;
        console.log(num);
        this.setState({ phoneNumber: num });
    }

    public updateCompany = async () => {
        try {
            let company = new Company(this.state.id, this.state.name,
                this.state.address, this.state.phoneNumber);
            await axios.put("http://localhost:8080/companies/", company);
            alert("Company updated!");

        } catch (err) {
            alert(err.message);
            console.log(err);
        }
    }

    public render() {
        return (
            <div className="updateCompanyDiv">
                <span>Id of company to update: <input className="updateCompanyInput" type="number" onChange={this.setId} /><br /><br /></span>
                <span>Name: <input className="updateCompanyInput" type="text" onChange={this.setName} /><br /><br /></span>
                <span>Address: <input className="updateCompanyInput" type="text" onChange={this.setAddress} /><br /><br /></span>
                <span>Phone number: <input className="updateCompanyInput" type="number" onChange={this.setPhoneNumber} /><br /><br /></span>
                <input className="updateCompanyButtonInCreate" type="button" value="Update company" onClick={this.updateCompany} />
            </div>
        );
    }
}