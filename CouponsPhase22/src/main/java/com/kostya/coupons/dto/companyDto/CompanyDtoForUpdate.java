package com.kostya.coupons.dto.companyDto;

public class CompanyDtoForUpdate {
	private long id;
	private String name;
	private String address;
	private long phoneNumber;
	
	public CompanyDtoForUpdate(long id, String name, String address, long phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public CompanyDtoForUpdate() {
		super();
	}
	
	@Override
	public String toString() {
		return "CompanyDtoForUpdate [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber="
				+ phoneNumber + "]";
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
