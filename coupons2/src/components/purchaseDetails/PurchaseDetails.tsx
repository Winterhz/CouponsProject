import axios from "axios";
import { Component } from "react";
import { FullPurchaseDetails } from "../../models/FullPurchaseDetails";
import "./PurchaseDetails.css";

interface IPurchaseDetailsState {
    purchase: FullPurchaseDetails;
}

export default class CouponDetails extends Component<any, IPurchaseDetailsState> {

    constructor(props: any) {
        super(props);
        this.state = {
            purchase: new FullPurchaseDetails()
        }
    }

    public async componentDidMount() {
        const id = this.props.match.params.id;
        let response;
        if (sessionStorage.getItem("userType") === "CUSTOMER") {
            response = await axios.get<FullPurchaseDetails>("http://localhost:8080/purchases/userGet/" + id);
        } else if (sessionStorage.getItem("userType") === "ADMIN") {
            response = await axios.get<FullPurchaseDetails>("http://localhost:8080/purchases/adminGet/" + id);
        } else if (sessionStorage.getItem("userType") === "COMPANY") {
            response = await axios.get<FullPurchaseDetails>("http://localhost:8080/purchases/companyGet/" + id);
        }
        this.setState({ purchase: response.data });
    }

    public render() {
        return (
            <div className="purchaseDetails">
                <p><span>Coupon name:</span>  {this.state.purchase.couponName}</p><br />
                <p><span>Time of purchase:</span>  {this.state.purchase.timeOfPurchase}</p><br />
                <p><span>Price:</span>  {this.state.purchase.price}</p><br />
                <p><span>description:</span>  {this.state.purchase.description}</p><br />
                <p><span>Start date:</span>  {this.state.purchase.startDate}</p><br />
                <p><span>End date:</span>  {this.state.purchase.endDate}</p><br />
                <p><span>Company name:</span>  {this.state.purchase.companyName}</p><br />
            </div>
        )
    }

}