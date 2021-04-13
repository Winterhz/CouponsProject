package com.kostya.coupons.dto.companyDto;

public class CompanyDto {
	private String name;
	private String address;
	private long phoneNumber;
	
	public CompanyDto(String name, String address, long phoneNumber) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public CompanyDto() {
		
	}
	
	@Override
	public String toString() {
		return "CompanyDto [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
