import axios from "axios";
import { ChangeEvent, Component } from "react";
import "./Purchases.css";

import { Purchase } from '../../models/Purchase';

interface IPurchasesProps {
    purchases: Purchase[];
    filterByCouponName: string;
    filterByUserId: number;
}

export default class Purchases extends Component<any, IPurchasesProps>{

    constructor(props: IPurchasesProps) {
        super(props);
        this.state = {
            purchases: [],
            filterByCouponName: "",
            filterByUserId: 0
        };
    }

    public async componentDidMount() {
        let response;
        if (sessionStorage.getItem("userType") === "CUSTOMER") {
            response = await axios.get<Purchase[]>("http://localhost:8080/purchases/userGetAll");
        } else if (sessionStorage.getItem("userType") === "COMPANY") {
            response = await axios.get<Purchase[]>("http://localhost:8080/purchases/companyGetAll");
        } else {
            response = await axios.get<Purchase[]>("http://localhost:8080/purchases/adminGetAll");
        }

        // response.data = all the coupons that were returned from the server
        this.setState({ purchases: response.data });
        /*store.dispatch({ type: ActionType.getCoupons, payload: response.data });*/
    }

    private onPurchaseClick = (id: number) => {
        return this.props.history.push("/purchases/" + id);
    }

    public byUserId = (event: ChangeEvent<HTMLInputElement>) => {
        let id = +event.target.value;
        console.log(id);
        this.setState({ filterByUserId: id });
    }
    public byCouponName = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ filterByCouponName: text });
    }


    public render() {
        return (
            <div className="purchases">
                <span className="purchasesInnerSpan">Search by Coupon Name:</span> <input className="searchPurchaseInPurchases" type="text" onChange={this.byCouponName} /> <br /><br />
                {sessionStorage.getItem("userType") === "ADMIN" && <div><span className="purchasesInnerSpan">Search by UserId:</span> <input className="searchPurchaseInPurchases" type="text" onChange={this.byUserId} /><br /><br /></div>}
                <div className="purchasesTable">
                    {sessionStorage.getItem("userType") === "CUSTOMER" && <h1>My purchases: </h1>}
                    {sessionStorage.getItem("userType") === "COMPANY" && <h1>Company purchases: </h1>}
                    {sessionStorage.getItem("userType") === "ADMIN" && <h1>All purchases: </h1>}
                    <table className="purchasesInfo">
                        <thead>
                            <tr>
                                <th>Coupon name</th>
                                <th>Amount</th>
                                <th>Time of purchase</th>
                                {sessionStorage.getItem("userType") === "ADMIN" && <th>User id</th>}
                                <th>view details</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.purchases.filter(purchase => {
                                    if (this.state.filterByCouponName === "") {
                                        return true;
                                    }

                                    return purchase.couponName.toLowerCase().includes(this.state.filterByCouponName.toLowerCase());
                                }).filter(purchase => {
                                    if (this.state.filterByUserId === 0) {
                                        return true;
                                    }

                                    return purchase.userId === this.state.filterByUserId;
                                }).map(purchase => (
                                    <tr key={purchase.id}>
                                        <td className="purchaseRow">{purchase.couponName}</td>
                                        <td className="purchaseRow">{purchase.amount}</td>
                                        <td className="purchaseRow">{purchase.timeOfPurchase}</td>
                                        {sessionStorage.getItem("userType") === "ADMIN" && <td className="purchaseRow">{purchase.userId}</td>}
                                        <td className="purchaseRow" id="showDetailsOfPurchase" onClick={() => this.onPurchaseClick(purchase.id)}>show details</td>
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