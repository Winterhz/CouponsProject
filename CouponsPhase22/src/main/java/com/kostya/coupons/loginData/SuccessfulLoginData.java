package com.kostya.coupons.loginData;

import com.kostya.coupons.enums.UserType;

public class SuccessfulLoginData {
	private UserType userType;
	private String token;
	private long id;
	private String userName;
	private String companyName;
	private String password;

	public SuccessfulLoginData(UserType userType, String token, long id, String userName, String companyName,
			String password) {
		super();
		this.userType = userType;
		this.token = token;
		this.id = id;
		this.userName = userName;
		this.companyName = companyName;
		this.password = password;
	}

	public SuccessfulLoginData(UserType userType, String token, long id, String userName) {
		super();
		this.userType = userType;
		this.token = token;
		this.id = id;
		this.userName = userName;
	}

	public SuccessfulLoginData( ) {
		
	}
	
	public UserType getUserType() {
		return userType;
	}
	
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
