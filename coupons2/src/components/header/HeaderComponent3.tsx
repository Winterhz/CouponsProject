import React from 'react';
import { Spring } from 'react-spring/renderprops-universal';

import "./HeaderComponent3.css";

export default function HeaderComponent3() {
    return (
        <Spring from={{ opacity: 0 }}
         to={{ opacity: 1 }}
         config={{ duration: 2000 }}>
            {props => (
                <div style={props}>

            <div className="headerComponent3">
                <br/>
                <h1>Our Contacts:</h1>
                <p>Phone number: +380507778723</p>
                <br/>
                <p>Email: kostya.asdD@gmail.com</p>
            </div>
            </div>
            )}
        </Spring>
    );
}