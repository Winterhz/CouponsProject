import axios from 'axios';
import React, { ChangeEvent, Component } from 'react';
import { Unsubscribe } from 'redux';
import { Coupon } from '../../models/Coupon';
import Card from '../card/Card';
import { ActionType } from '../redux/ActionType';
import { store } from '../redux/store';
import "./Coupons.css";

interface ICouponsState {
    coupons: Coupon[];
    filterByName: string;
    filterByCategory: string;
    filterByPrice: number;
    filterById: number;
    filterByCompanyName: string;
}

export default class Coupons extends Component<any, ICouponsState>{

    private unsubscribeStore: Unsubscribe;

    constructor(props: any) {
        super(props);
        this.state = {
            coupons: store.getState().coupons,
            filterByName: "",
            filterByCategory: "",
            filterByPrice: 0,
            filterById: 0,
            filterByCompanyName: ""
        }
        this.unsubscribeStore = store.subscribe(() => this.setState(
            {
                coupons: store.getState().coupons
            }
        )
        )
    }

    componentWillUnmount() {
        this.unsubscribeStore();
    }

    public async componentDidMount() {
        let response;
        if (sessionStorage.getItem("userType") === "COMPANY") {
            this.setState({ filterByCompanyName: store.getState().userDetails.companyName });
            response = await axios.get<Coupon[]>("http://localhost:8080/coupons/couponsByCompanyName?name=" + store.getState().userDetails.companyName);
        } else {
            response = await axios.get<Coupon[]>("http://localhost:8080/coupons");
        }

        // response.data = all the coupons that were returned from the server
        store.dispatch({ type: ActionType.getCoupons, payload: response.data });
    }

    public goToCouponCreation = () => {
        this.props.history.push("/createCoupon");
    }

    public byCouponName = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ filterByName: text });
    }
    public onCategorySelected = (event: ChangeEvent<HTMLSelectElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ filterByCategory: text });
    }
    public byMaxPrice = (event: ChangeEvent<HTMLInputElement>) => {
        let price = +event.target.value;
        console.log(price);
        console.log(store.getState().showComponent3);
        this.setState({ filterByPrice: price });
    }
    public byId = (event: ChangeEvent<HTMLInputElement>) => {
        let id = +event.target.value;
        console.log(id);
        this.setState({ filterById: id });
    }
    public byCompanyName = (event: ChangeEvent<HTMLInputElement>) => {
        let text = event.target.value;
        console.log(text);
        this.setState({ filterByCompanyName: text });
    }

    public render() {
        return (
            <div className="coupons" >
                {sessionStorage.getItem("userType") === "ADMIN" && <span>Search by id: <input className="searchInputId" type="number" onChange={this.byId} /><br /><br /></span>}
                Search by coupon name: <input className="searchInputName" type="text" onChange={this.byCouponName} />
                Search by company name: <input className="searchInputName" type="text" onChange={this.byCompanyName} />
                <select className="selectCategory" onChange={this.onCategorySelected} >
                    <option className="categoryOption">All</option>
                    <option className="categoryOption">Food</option>
                    <option className="categoryOption">Spa</option>
                    <option className="categoryOption">Electronics</option>
                    <option className="categoryOption">Sport</option>
                    <option className="categoryOption">Vacation</option>
                </select> <br /><br />
                Search by max price: <input className="searchInputPrice" type="number" onChange={this.byMaxPrice} />
                {sessionStorage.getItem("userType") === "COMPANY" && <input className="createCouponButton" type="button" value="Create new coupon" onClick={this.goToCouponCreation} />}
                {<ol>
                    {this.state.coupons.filter(coupon => {
                        if (this.state.filterById === 0) {
                            return true;
                        }

                        return coupon.id === this.state.filterById;
                    }).filter(coupon => {
                        if (this.state.filterByName === "") {
                            return true;
                        }

                        return coupon.name.toLowerCase().includes(this.state.filterByName.toLowerCase());
                    }).filter(coupon => {
                        if (this.state.filterByCompanyName === "") {
                            return true;
                        }

                        return coupon.companyName.toLowerCase().includes(this.state.filterByCompanyName.toLowerCase());
                    }).filter(coupon => {
                        if (this.state.filterByCategory === "") {
                            return true;
                        }

                        return coupon.categoryType.toLowerCase().includes(this.state.filterByCategory.toLowerCase());
                    }).filter(coupon => {
                        if (this.state.filterByPrice === 0) {
                            return true;
                        }

                        return coupon.price >= this.state.filterByPrice;
                    }).map(coupon => <Card key={coupon.id} coupon={coupon} props={this.props} />)}

                </ol>}
            </div>
        );
    }
}