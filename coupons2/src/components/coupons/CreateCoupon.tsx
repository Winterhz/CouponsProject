import axios from "axios";
import { ChangeEvent, Component } from "react";
import { FullCouponDetails } from "../../models/FullCouponDetails";
import { store } from "../redux/store";
import "./CreateCoupon.css";

interface ICreateCouponState {
    name: string;
    amount: number;
    price: number;
    description: string;
    startDate: Date;
    endDate: Date;
    categoryType: string;
    image: string;
    companyName: string;
}

export default class CreateCoupon extends Component<any, ICreateCouponState>{

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
            image: "",
            companyName: store.getState().userDetails.companyName
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

    public setCompanyName = (event: ChangeEvent<HTMLInputElement>) => {
        let text = store.getState().userDetails.companyName;
        console.log(text);
        this.setState({ companyName: text });
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
                this.state.startDate, this.state.endDate, this.state.categoryType.toUpperCase(), this.state.image, this.state.companyName);
            await axios.post("http://localhost:8080/coupons/", coupon);
            console.log("22222222222222222222222222222222222222222222222222222222222222");
            alert("Coupon created!");

        } catch (err) {
            alert(err.message);
            console.log(err);
        }
    }

    public render() {
        return (
            <div className="createCouponDiv">
                <span>Coupon name: <input className="createCouponInput" type="text" onChange={this.setName} /><br /><br /></span>
                <span>Amount of coupons: <input className="createCouponInput" type="number" onChange={this.setAmount} /><br /><br /></span>
                <span>Price: <input className="createCouponInput" type="number" onChange={this.setPrice} /><br /><br /></span>
                <span>Description: <input className="createCouponInput" type="text" onChange={this.setDescription} /><br /><br /></span>
                <span>Start date: <input className="createCouponInput" type="date" onChange={this.setEndDate} /><br /><br /></span>
                <span>End date: <input className="createCouponInput" type="date" onChange={this.setStartDate} /><br /><br /></span>
                <span>Category: </span>
                <select className="createCouponSelect" onChange={this.setCategory} >
                    <option className="categoryOptionInCreate">Food</option>
                    <option className="categoryOptionInCreate">Spa</option>
                    <option className="categoryOptionInCreate">Electronics</option>
                    <option className="categoryOptionInCreate">Sport</option>
                    <option className="categoryOptionInCreate">Vacation</option>
                </select> <br /><br />
                <span>Image: <input className="createCouponInput" type="text" onChange={this.setImage} /><br /><br /></span>
                <input className="createCouponButtonInCreate" type="button" value="Create coupon" onClick={this.createCoupon} />
            </div>
        );
    }
}