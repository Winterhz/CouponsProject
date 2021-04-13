package com.kostya.coupons.dto.errorDto;

import com.kostya.coupons.enums.ErrorType;

public class ErrorBean {
	private ErrorType errorType;
	private int errorCode;
	private String errorMessage;
	
	public ErrorBean(int errorCode, String errorName, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ErrorBean(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ErrorBean() {
		super();
	}

	@Override
	public String toString() {
		return "ErrorBean [errorType=" + errorType + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage
				+ "]";
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
