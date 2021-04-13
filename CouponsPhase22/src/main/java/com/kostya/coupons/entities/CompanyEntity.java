package com.kostya.coupons.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kostya.coupons.dto.companyDto.CompanyDto;
import com.kostya.coupons.dto.companyDto.CompanyDtoForUpdate;

@Entity
@Table(name = "companies")
public class CompanyEntity {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone_number")
	private long phoneNumber;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<UserEntity> users;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<CouponEntity> coupons;

	public CompanyEntity(CompanyDto companyDto) {
		super();
		this.name = companyDto.getName();
		this.address = companyDto.getAddress();
		this.phoneNumber = companyDto.getPhoneNumber();
	}

	public CompanyEntity(CompanyDtoForUpdate companyDtoForUpdate) {
		super();
		this.id = companyDtoForUpdate.getId();
		this.name = companyDtoForUpdate.getName();
		this.address = companyDtoForUpdate.getAddress();
		this.phoneNumber = companyDtoForUpdate.getPhoneNumber();
	}

	public CompanyEntity() {
		super();
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public List<CouponEntity> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CouponEntity> coupons) {
		this.coupons = coupons;
	}
	
}
