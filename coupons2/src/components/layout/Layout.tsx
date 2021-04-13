import axios from 'axios';
import React, { Component } from 'react';
import '../library/icons';
import { BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';
import About from '../about/about';
import Card from '../card/Card';
import Companies from '../companies/Companies';
import CouponDetails from '../CouponDetails/CouponDetails';
import Coupons from '../coupons/Coupons';
import CreateCoupon from '../coupons/CreateCoupon';
import UpdateCoupon from '../coupons/UpdateCoupon';
import Footer from '../footer/Footer';
import Header from '../header/Header';
import Login from '../login/Login';
import Menu from '../menu/Menu';
import Profile from '../profile/Profile';
import UpdateProfile from '../profile/UpdateProfile';
import PurchaseDetails from '../purchaseDetails/PurchaseDetails';
import Purchases from '../purchases/Purchases';
import Users from '../users/Users';
import "./Layout.css";
import CreateUser from '../users/CreateUser';
import CreateCompany from '../companies/CreateCompany';
import UpdateCompany from '../companies/UpdateCompany';

export default class Layout extends Component {

  public componentDidUpdate() {

    let token = sessionStorage.getItem("token");
    console.log("token = " + token);
    axios.defaults.headers.common['Authorization'] = token;

  }

  public render() {
    return (
      <BrowserRouter>
        <div>
          <section className="layout">
            <header>
             <Header />  
            </header>

            <aside>
              <Menu />
            </aside>

            <main>
              <Switch>
                <Route path="/purchases" component={Purchases} exact />
                <Route path="/about" component={About} exact />
                <Route path="/home" component={Coupons} exact />
                <Route path="/login" component={Login} exact />
                <Route path="/coupons" component={Coupons} exact />
                <Route path="/coupons/:id" component={CouponDetails} exact />
                <Route path="/purchases/:id" component={PurchaseDetails} exact />
                <Route path="/cards" component={Card} exact />
                <Route path="/coupons" component={Coupons} exact />
                <Route path="/companies" component={Companies} exact />
                <Route path="/updateCompany" component={UpdateCompany} exact />
                <Route path="/createCompany" component={CreateCompany} exact />
                <Route path="/updateProfile" component={UpdateProfile} exact />
                <Route path="/profile" component={Profile} exact />
                <Route path="/createCoupon" component={CreateCoupon} exact />
                <Route path="/updateCoupon" component={UpdateCoupon} exact />
                <Route path="/users" component={Users} exact />
                <Route path="/createUser" component={CreateUser} exact />
                <Redirect from="/" to="/home" exact />
                {/* <Route component={PageNotFound} /> */}
              </Switch>

            </main>

            <footer>
              <Footer />
            </footer>
          </section>
        </div>
      </BrowserRouter>
    );
  }
}

