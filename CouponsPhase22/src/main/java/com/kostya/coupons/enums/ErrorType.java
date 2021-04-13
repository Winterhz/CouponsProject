package com.kostya.coupons.enums;

public enum ErrorType {
	GENERAL_ERROR(901, "General error", true),
	IS_EXISTS_ERROR(902, "is exist error", false),
	LOGIN_ERROR(903, "login error", false),
	NULL_NAME_ERROR(904, "null name error", false),
	LENGTH_ERROR(905,"length error", false),
	PRICE_ERROR(906, "price error", false),
	AMOUNT_ERROR(907, "amount error", false),
	IS_AVAILABLE_ERROR(908, "is available error", false),
	OUTDATED_COUPON_ERROR(909, "coupon is outdate", false),
	INVALID_USER_TYPE(910, "invalid user type", false);
	
	private String name;
	private boolean isShowStackTrace;
	private String errorMessage;
	private int errorNumber;
	
	private ErrorType(int errorNumber, String errorMessage, boolean isShowStackTrace) {
		this.isShowStackTrace = isShowStackTrace;
		this.errorMessage = errorMessage;
		this.errorNumber = errorNumber;
	}

	public boolean isShowStackTrace() {
		return isShowStackTrace;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

	public String getName() {
		return name;
	}
	
}
