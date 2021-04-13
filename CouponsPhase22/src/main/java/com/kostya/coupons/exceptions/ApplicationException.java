package com.kostya.coupons.exceptions;

import com.kostya.coupons.enums.ErrorType;

public class ApplicationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorType errorType;

	// ctor if i dont need to know why is exception happened
	public ApplicationException(ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}
	// ctor when i want to know what happened that i got exception
	public ApplicationException(Exception e,ErrorType errorType, String message) {
		super(message, e);
		this.errorType = errorType;
	}
	//default ctor
	public ApplicationException() {
		
	}
	
	public ErrorType getErrorType() {
		return errorType;
	}
	
}
