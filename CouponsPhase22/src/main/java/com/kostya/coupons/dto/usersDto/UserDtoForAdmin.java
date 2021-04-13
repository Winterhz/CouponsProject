package com.kostya.coupons.dto.usersDto;

import com.kostya.coupons.enums.UserType;

public class UserDtoForAdmin {
	private long id;
	private String userName;
	private UserType userType;
	private long companyId;
	private String companyName;

	public UserDtoForAdmin(long id, String userName, UserType userType, long companyId) {
		super();
		this.id = id;
		this.userName = userName;
		this.userType = userType;
		this.companyId = companyId;
	}

	public UserDtoForAdmin(long id, String userName, UserType userType, String companyName) {
		super();
		this.id=id;
		this.userName = userName;
		this.userType = userType;
		this.companyName = companyName;
	}

	public UserDtoForAdmin(long id, String userName, UserType userType) {
		super();
		this.id = id;
		this.userName = userName;
		this.userType = userType;
	}

	public UserDtoForAdmin() {
	}

	@Override
	public String toString() {
		return "UserDto [userName=" + userName + ", userType=" + userType + ", companyName="
				+ companyName + "]";
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
}
