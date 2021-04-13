import React from 'react';
import { Spring } from 'react-spring/renderprops-universal';
import { ActionType } from '../redux/ActionType';
import { store } from '../redux/store';

import "./HeaderComponent2.css";

export default function HeaderComponent2() {

    return (
        <Spring
        from={{ opacity: 0, marginTop: -1000 }}
         to={{ opacity: 1, marginTop: 0 }}
         config={{ delay: 2000}}>
            {props => (
                <div style={props}>

            <div className="headerComponent2">
                <p>Always feel free to contact us!</p>
                <button className="headerComponent2Button" onClick={()=>{store.dispatch({ type: ActionType.getComponent3, payload: !(store.getState().showComponent3) })}}>show contacts</button>
            </div>
            </div>
            )}
        </Spring>
    );
}