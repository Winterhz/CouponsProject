import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React from 'react';
import { Spring } from 'react-spring/renderprops-universal';

import "./HeaderComponent1.css";

export default function HeaderComponent1() {
    return (
        <Spring from={{ opacity: 0 }}
        to={{ opacity: 1 }}
        config={{ duration: 2000 }}>
            {props => (
                <div style={props}>

            <div className="headerComponent1">
                <br/>
                <br/>
                <h1>Always stay in charge with us!  </h1>
                <FontAwesomeIcon icon="battery-full" className="iconBattery" size="4x" />
            </div>
            </div>
            )}
        </Spring>
    );
}