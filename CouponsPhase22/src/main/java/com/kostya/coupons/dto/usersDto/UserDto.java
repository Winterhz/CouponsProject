package com.kostya.coupons.dto.usersDto;

import com.kostya.coupons.enums.UserType;

public class UserDto {
	private String userName;
	private String password;
	private UserType userType;
	private String companyName;
	
	public UserDto(String userName, String password, UserType userType, String companyName) {
		super();
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.companyName = companyName;
	}

	public UserDto() {
		super();
	}

	@Override
	public String toString() {
		return "UserDto [userName=" + userName + ", password=" + password + ", userType=" + userType + ", companyName="
				+ companyName + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
