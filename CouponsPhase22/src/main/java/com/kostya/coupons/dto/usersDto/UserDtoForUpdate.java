package com.kostya.coupons.dto.usersDto;

public class UserDtoForUpdate {
	private String userName;
	private String password;
	
	public UserDtoForUpdate(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public UserDtoForUpdate() {
		super();
	}

	@Override
	public String toString() {
		return "UserDtoForUpdate [userName=" + userName + ", password=" + password + "]";
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
	
}
