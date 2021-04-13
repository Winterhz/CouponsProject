package com.kostya.coupons.loginData;

import com.kostya.coupons.enums.UserType;

public class UserLoginData {
	private long userId;
	private UserType userType;
	private Long companyId;

	public UserLoginData(long userId, UserType userType, long companyId) {
		super();
		this.userId = userId;
		this.userType = userType;
		this.companyId = companyId;
	}
	public UserLoginData(long userId, UserType userType) {
		super();
		this.userId = userId;
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserLoginData [userId=" + userId + ", userType=" + userType + ", companyId=" + companyId + "]";
	}
	
	public long getUserId() {
		return userId;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public long getCompanyId() {
		return companyId;
	}
}
