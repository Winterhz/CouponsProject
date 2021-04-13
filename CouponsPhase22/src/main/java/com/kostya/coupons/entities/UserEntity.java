package com.kostya.coupons.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kostya.coupons.dto.usersDto.UserDto;
import com.kostya.coupons.dto.usersDto.UserDtoForAdmin;
import com.kostya.coupons.dto.usersDto.UserDtoForUpdate;
import com.kostya.coupons.enums.UserType;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 112314151512312L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "user_name", nullable = false, unique = true)
	private String userName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "user_type")
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<PurchaseEntity> purchases;
	
	@ManyToOne()
	@JsonIgnore
	private CompanyEntity company;

	public UserEntity(long id, String userName, String password, UserType userType, Long companyId) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}
	
	public UserEntity(UserDtoForAdmin userDtoForAdmin) {
		this.id = userDtoForAdmin.getId();
		this.userName = userDtoForAdmin.getUserName();
		this.userType = userDtoForAdmin.getUserType();
	}
	
	public UserEntity(UserDto userDto) {
		this.userName = userDto.getUserName();
		this.password = userDto.getPassword();
		this.userType = userDto.getUserType();
	}

	public UserEntity(UserDtoForUpdate userDtoForUpdate) {
		super();
		this.userName = userDtoForUpdate.getUserName();
		this.password = userDtoForUpdate.getPassword();
	}

	public UserEntity() {
		super();
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", userName=" + userName + ", password=" + password + ", userType=" + userType
				+ ", purchases=" + purchases + ", company=" + company + "]";
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

	public List<PurchaseEntity> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseEntity> purchases) {
		this.purchases = purchases;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}
	
}
