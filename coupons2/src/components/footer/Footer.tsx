import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { Component } from 'react'
import "./Footer.css";

export default class Footer extends Component {
    public render() {
        return (
          <div className="footer">
            <p className="footerBG">All rights reserved</p>
            <a href="https://facebook.com"><FontAwesomeIcon icon={["fab", "facebook"]} className="iconBrand" size="2x" /></a>
            <a href="https://youtube.com"><FontAwesomeIcon icon={["fab", "youtube"]} className="iconBrand" size="2x" /></a>
            <a href="https://twitter.com"><FontAwesomeIcon icon={["fab", "twitter"]} className="iconBrand" size="2x" /></a>
            <a href="https://instagram.com"><FontAwesomeIcon icon={["fab", "instagram"]} className="iconBrand" size="2x" /></a>
          </div>
        );
    }
  }

