import axios from "axios";
import { ChangeEvent, Component } from "react";
import { Company } from "../../models/Company";
import "./CreateCompany.css";

interface ICreateUserState {
    name:string;
    address:string;
    phoneNumber:number;
}

export default class CreateUser extends Component<any, ICreateUserState>{

    constructor(props: any) {
        super(props);
        this.state = {
            name: "",
            address: "",
            phoneNumber: 0
        }
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

    public createCompany = async () => {
        try {
            let company = new Company(null, this.state.name,
                this.state.address, this.state.phoneNumber);
            await axios.post("http://localhost:8080/companies/", company);
            alert("Company created!");

        } catch (err) {
            alert(err.message);
            console.log(err);
        }
    }

    public render() {
        return (
            <div className="createCompanyDiv">
                <span>Name: <input className="createCompanyInput" type="text" onChange={this.setName} /><br /><br /></span>
                <span>Address: <input className="createCompanyInput" type="text" onChange={this.setAddress} /><br /><br /></span>
                <span>Phone number: <input className="createCompanyInput" type="number" onChange={this.setPhoneNumber} /><br /><br /></span>
                <input className="createCompanyButtonInCreate" type="button" value="Create company" onClick={this.createCompany} />
            </div>
        );
    }
}