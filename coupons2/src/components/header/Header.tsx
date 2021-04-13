import React, { Component } from 'react';
import { Unsubscribe } from 'redux';

import { store } from '../redux/store';
import "./Header.css";
import HeaderComponent1 from './HeaderComponent1';
import HeaderComponent2 from './HeaderComponent2';
import HeaderComponent3 from './HeaderComponent3';

interface IHeaderState {  
  showComponent3: boolean;
}

export default class Header extends Component<any, IHeaderState> {

  private unsubscribeStore: Unsubscribe;

  constructor(props: boolean) {
    super(props);
    this.state = {
      showComponent3: store.getState().showComponent3
    }
    this.unsubscribeStore = store.subscribe(() => this.setState(
      {
        showComponent3: store.getState().showComponent3
      }
    )
    )
  }

  componentWillUnmount(){
    this.unsubscribeStore();
  }

  public render() {
    return (
      <div className="header">
        <div className="headerComponent1Div">
          <HeaderComponent1 />
        </div>
        <div className="headerComponent2Div">
          <HeaderComponent2 />
        </div>
        { store.getState().showComponent3 &&
        <div className="headerComponent3Div">
          <HeaderComponent3 />
        </div>}
      </div>
    );
  }
}