import axios from "axios";
import { ChangeEvent, Component } from "react";
import { Unsubscribe } from "redux";
import { Company } from "../../models/Company";
import { ActionType } from "../redux/ActionType";
import { store } from "../redux/store";

import "./Companies.css";

interface ICompaniesState {
    companies: Company[];
    filterById: number;
    filterByName: string;
}

export default class Coupons extends Component<any, ICompaniesState>{

    private unsubscribeStore: Unsubscribe;

    constructor(props: any) {
        super(props);
        this.state = {
            companies: store.getState().companies,
            filterById: 0,
            filterByName: ""
        }
        this.unsubscribeStore = store.subscribe(() => this.setState(
            {
              companies: store.getState().companies
            }
          )
          )
    }

    public async componentDidMount() {
        const response = await axios.get<Company[]>("http://localhost:8080/companies/forAdmin");
        console.log(response);

        // response.data = all the coupons that were returned from the server
        //this.setState({ companies: response.data });
        store.dispatch({ type: ActionType.getCompanies, payload: response.data });
    }

    componentWillUnmount(){
        this.unsubscribeStore();
    }

    public goToCompanyCreation = () => {
        this.props.history.push("/createCompany");
    }

    public goToCompanyUpdate = () => {
        this.props.history.push("/updateCompany");
    }

    public deleteCompany = async (id: number) => {
        await axios.delete("http://localhost:8080/companies/" + id);
        store.dispatch({ type: ActionType.deleteCompany, payload: id });
        alert("Company is deleted!");
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

    public render() {
        return (
            <div className="companies">
                <span>Search by Name:</span> <input className="searchCompanyInCompanies" type="text" onChange={this.byName} />
                <span>Search by id:</span> <input className="searchCompanyInCompanies" type="number" onChange={this.byId} /><br /><br />
                {sessionStorage.getItem("userType")==="ADMIN" && <div><input className="createNewCompanyForAdmin" type="button" value="Create new company" onClick={this.goToCompanyCreation} />
                <input className="updateCompanyForAdmin" type="button" value="Update company" onClick={this.goToCompanyUpdate} /><br /><br /></div>}
                <div className="companiesTable">
                    <h1>All Companies: </h1>
                    <table className="companiesInfo">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Address</th>
                                <th>Phone Number</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.companies.filter(company => {
                                    if (this.state.filterById === 0) {
                                        return true;
                                    }

                                    return company.id === this.state.filterById;
                                }).filter(company => {
                                    if (this.state.filterByName === "") {
                                        return true;
                                    }

                                    return company.name.toLowerCase().includes(this.state.filterByName.toLowerCase());
                                }).map(company => (
                                    <tr key={company.id}>
                                        <td className="companyRow">{company.id}</td>
                                        <td className="companyRow">{company.name}</td>
                                        <td className="companyRow">{company.address}</td>
                                        <td className="companyRow">{company.phoneNumber}</td>
                                        <td className="companyRow" id="deleteCompanyForAdmin" onClick={() => this.deleteCompany(company.id)}>delete company</td>
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