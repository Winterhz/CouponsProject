import axios from "axios";
import { ChangeEvent, Component } from "react";
import { FullCouponDetails } from "../../models/FullCouponDetails";
import { Purchase } from "../../models/Purchase";
import "./CouponDetails.css";

interface ICouponDetailsState {
    coupon: FullCouponDetails;
    amount: number;
}

export default class CouponDetails extends Component<any, ICouponDetailsState> {

    constructor(props: any) {
        super(props);
        this.state = {
            coupon: new FullCouponDetails(),
            amount: 0
        }

    }

    public async componentDidMount() {
        const id = this.props.match.params.id;
        const response = await axios.get<FullCouponDetails>("http://localhost:8080/coupons/" + id);
        this.setState({ coupon: response.data });
    }

    public createPurchase = async () => {
        try {
            let purchase = new Purchase(null, this.state.amount, null, this.state.coupon.name);
            await axios.post("http://localhost:8080/purchases/", purchase);
            alert("Purchase created!");

        } catch (err) {
            alert(err.message);
            console.log(err);
        }
    }

    public amountToPurhase = (event: ChangeEvent<HTMLInputElement>) => {
        let num = +event.target.value;
        console.log(num);
        this.setState({ amount: num });
    }

    public deleteCoupon = async () => {
        const id = this.state.coupon.id;
        await axios.delete("http://localhost:8080/coupons/" + id);
        this.props.history.push("/coupons/");
    }

    public updateCoupon = async () => {
        this.props.history.push("/updateCoupon/");
    }

    public render() {
        return (
            <div className="couponDetails">
                <div className="infoAboutCoupon">
                    <p><span>Name:</span>  {this.state.coupon.name}</p>
                    <p><span>Amount:</span>  {this.state.coupon.amount}</p>
                    <p><span>Price:</span>  {this.state.coupon.price}</p>
                    <p><span>Description:</span>  {this.state.coupon.description}</p>
                    <p><span>Start date:</span>  {this.state.coupon.startDate}</p>
                    <p><span>End date:</span>  {this.state.coupon.endDate}</p>
                    <p><span>Category:</span>  {this.state.coupon.categoryType}</p>
                    <p><span>Company name:</span>  {this.state.coupon.companyName}</p>
                    {sessionStorage.getItem("userType") === "CUSTOMER" && <div><span>Amount of coupons to purchase: </span><br /><input className="amountToPurchase" type="number" onChange={this.amountToPurhase} /> <br /><br /></div>}
                    {sessionStorage.getItem("userType") === "CUSTOMER" && <input className="couponDetailsButton" type="button" value="Purchase" onClick={this.createPurchase} />}
                    {sessionStorage.getItem("userType") === "ADMIN" && <input className="couponDetailsButton" type="button" value="Delete Coupon" onClick={this.deleteCoupon} />}
                    {sessionStorage.getItem("userType") === "COMPANY" && <input className="couponDetailsButton" type="button" value="Update Coupon" onClick={this.updateCoupon} />}
                    {sessionStorage.getItem("userType") === "COMPANY" && <input className="couponDetailsButton" id="updateCouponButton" type="button" value="Delete Coupon" onClick={this.deleteCoupon} />}
                </div>
                <div className="pictureOfCoupon" style={{
                    backgroundImage: 'url(' + this.state.coupon.image + ')'
                }}>
                </div>
            </div>
        )
    }
}