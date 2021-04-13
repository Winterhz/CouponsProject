import axios from "axios";
import { ChangeEvent, Component } from "react";
import { FullCouponDetails } from "../../models/FullCouponDetails";
import "./UpdateCoupon.css";

interface IUpdateCouponState {
    name: string;
    amount: number;
    price: number;
    description: string;
    startDate: Date;
    endDate: Date;
    categoryType: string;
    image: string;
}

export default class UpdateCoupon extends Component<any, IUpdateCouponState>{

    constructor(props: any) {
        super(props);
        this.state = {
            name: "",
            amount: 0,
            price: 0,
            description: "",
            startDate: null,
            endDate: null,
            categoryType: "",
            image: ""
        }
    }

    public setName = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ name: text });
    }

    public setAmount = (event: ChangeEvent<HTMLInputElement>) => {
        let number = +event.target.value;
        console.log(number);
        this.setState({ amount: number });
    }

    public setPrice = (event: ChangeEvent<HTMLInputElement>) => {
        let number = +event.target.value;
        console.log(number);
        this.setState({ price: number });
    }

    public setDescription = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ description: text });
    }

    public setCategory = (event: ChangeEvent<HTMLSelectElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ categoryType: text }); //cant send categoryType but string
    }

    public setImage = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ image: text });
    }

    public setStartDate = (event: ChangeEvent<HTMLInputElement>) => {
        let text = new Date(event.target.value);
        console.log(text);
        this.setState({ startDate: text });
    }

    public setEndDate = (event: ChangeEvent<HTMLInputElement>) => {
        let text = new Date(event.target.value);
        console.log(text);
        this.setState({ endDate: text });
    }

    public createCoupon = async () => {
        try {
            let coupon = new FullCouponDetails(null, this.state.name, this.state.amount, this.state.price, this.state.description,
                this.state.startDate, this.state.endDate, this.state.categoryType, this.state.image);
            await axios.put("http://localhost:8080/coupons/", coupon);
            console.log("22222222222222222222222222222222222222222222222222222222222222");
            alert("Coupon updated!");

        } catch (err) {
            alert(err.message);
            console.log(err);
        }
    }

    public render() {
        return (
            <div className="updateCouponDiv">
                <span>Coupon name: <input className="updateCouponInput" type="text" onChange={this.setName} /><br /><br /></span>
                <span>Amount of coupons: <input className="updateCouponInput" type="number" onChange={this.setAmount} /><br /><br /></span>
                <span>Price: <input className="updateCouponInput" type="number" onChange={this.setPrice} /><br /><br /></span>
                <span>Description: <input className="updateCouponInput" type="text" onChange={this.setDescription} /><br /><br /></span>
                <span>Start date: <input className="updateCouponInput" type="date" onChange={this.setEndDate} /><br /><br /></span>
                <span>End date: <input className="updateCouponInput" type="date" onChange={this.setStartDate} /><br /><br /></span>
                <span>Category: </span>
                <select className="createCouponSelect" onChange={this.setCategory} >
                    <option className="categoryOptionInUpdate">Food</option>
                    <option className="categoryOptionInUpdate">Spa</option>
                    <option className="categoryOptionInUpdate">Electronics</option>
                    <option className="categoryOptionInUpdate">Sport</option>
                    <option className="categoryOptionInUpdate">Vacation</option>
                </select> <br /><br />
                <span>Image: <input className="updateCouponInput" type="text" onChange={this.setImage} /><br /><br /><br /><br /></span>
                <input className="updateCouponButtonInUpdate" type="button" value="Update coupon" onClick={this.createCoupon} />
            </div>
        );
    }
}