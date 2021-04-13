import React, { Component } from 'react'
import { Coupon } from '../../models/Coupon';
import "./Card.css";
import Tilt from 'react-tilt';

interface ICardProps {
    coupon: Coupon;
    props: any;
}

export default class Card extends Component<ICardProps> {

    constructor(props: ICardProps) {
        super(props);
    }

    private onCouponClick = () => {
        this.props.props.history.push("/coupons/" + this.props.coupon.id);
    }

    public render() {
        return (
            <div className="container">
                <Tilt className="cardTilt">

                    <div className="card">
                        <div className="topCard" style={{
                            backgroundImage: 'url(' + this.props.coupon.image + ')'
                        }}></div>
                        <p>Name: <span className="couponInfo">{this.props.coupon.name}</span></p>
                        <p>Price: <span className="couponInfo">{this.props.coupon.price}</span></p>
                        <p>Amount: <span className="couponInfo">{this.props.coupon.amount}</span></p>
                        <input className="showDetailsAboutCouponCard" type="button" value="Show Details" onClick={this.onCouponClick} />
                    </div>

                </Tilt>
            </div >
        );
    }
}