package com.kostya.coupons.dto.usersDto;

import com.kostya.coupons.enums.UserType;

//classes UserDtoForCompany and UserDtoForUser are equal, but i need them for flexibility and future changes
public class UserDtoForCompany {
	private String userName;
	private UserType userType;
	
	public UserDtoForCompany(String userName, UserType userType) {
		super();
		this.userName = userName;
		this.userType=userType;
	}

	public UserDtoForCompany() {
		super();
	}

	@Override
	public String toString() {
		return "UserDtoForCompany [userName=" + userName + ", userType=" + userType + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
}
