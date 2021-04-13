package com.kostya.coupons.dto.usersDto;

import com.kostya.coupons.enums.UserType;

public class UserDtoForUser {
	private String userName;
	private UserType userType;

	public UserDtoForUser(String userName, UserType userType) {
		super();
		this.userName = userName;
		this.userType = userType;
	}

	public UserDtoForUser() {
		super();
	}

	@Override
	public String toString() {
		return "UserDtoForUser [userName=" + userName + ", userType=" + userType + "]";
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
