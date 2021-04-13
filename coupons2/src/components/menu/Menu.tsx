import axios from 'axios';
import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';
import { Unsubscribe } from 'redux';
import { ActionType } from '../redux/ActionType';
import { store } from '../redux/store';
import "./Menu.css";

interface IMenuState {
  isLoggedIn: boolean;
}

export default class Menu extends Component<any, IMenuState> {

  private unsubscribeStore: Unsubscribe;

  constructor(props: any) {
    super(props);

    this.state = {
      isLoggedIn: store.getState().isLoggedIn
    }
    this.unsubscribeStore = store.subscribe(() => this.setState(
      {
        isLoggedIn: store.getState().isLoggedIn
      }
    )
    )
  }

  componentWillUnmount(){
    this.unsubscribeStore();
  }

  public logout = async () => {
    store.dispatch({type: ActionType.Logout});
    await axios.post<null>("http://localhost:8080/users/logout", axios.defaults.headers.common["Authorization"]);
    sessionStorage.removeItem("userType");
    sessionStorage.removeItem("token");
  }

  public render() {
    return (
      <div className="menu">
        {this.state.isLoggedIn && <div id="menuInnerDiv">
          <NavLink to="/purchases" exact>Purchases</NavLink>
        </div>}


        {sessionStorage.getItem("userType")==="ADMIN" && <div id="menuInnerDiv">
          <NavLink to="/companies" exact>Companies</NavLink>
        </div>}
        {sessionStorage.getItem("userType")==="ADMIN" && <div id="menuInnerDiv">
          <NavLink to="/users" exact>Users</NavLink>
        </div>}

        {this.state.isLoggedIn && <div id="menuInnerDiv">
          <NavLink to="/profile" exact>My profile</NavLink>
        </div>}

        <div id="menuInnerDiv">
          <NavLink to="/coupons" exact>Coupons</NavLink>
        </div>
        <div id="menuInnerDiv">
          <NavLink to="/about" exact>About</NavLink>
        </div>

        {!(this.state.isLoggedIn) && <div id="menuInnerDiv">
          <NavLink to="/login" exact>Login</NavLink>
        </div>}
        {this.state.isLoggedIn && <div id="menuInnerDiv" onClick={this.logout}>
          <NavLink to="/home" exact>Logout</NavLink>
        </div>}

      </div >
    );
  }
}

