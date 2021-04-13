package com.kostya.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kostya.coupons.loginData.UserLoginData;
import com.kostya.coupons.dto.usersDto.UserDtoForAdmin;
import com.kostya.coupons.dto.usersDto.UserDtoForCompany;
import com.kostya.coupons.dto.usersDto.UserDtoForUser;
import com.kostya.coupons.entities.UserEntity;

@Repository
public interface IUsersDao extends CrudRepository<UserEntity, Long>{

	@Query("select new com.kostya.coupons.loginData.UserLoginData(u.id, u.userType) from UserEntity u" //,u.company.id
			+ " where u.userName = ?1 and u.password = ?2")
	public UserLoginData login(String name, String password);
	
	@Query("select u.company.id from UserEntity u where u.id=?1")
	public long getCompanyIdByUserId(long id);
	
	public boolean existsByUserName(String name);
	
	@Query("select new com.kostya.coupons.dto.usersDto.UserDtoForUser(u.userName, u.userType) from UserEntity u"
			+ " where u.userName = ?1")
	public UserDtoForUser findUserForUser(String name);
	
	@Query("select new com.kostya.coupons.dto.usersDto.UserDtoForCompany(u.userName, u.userType) from UserEntity u"
			+ " where u.userName = ?1")
	public UserDtoForCompany findUserForCompany(String name);
	
	@Query("select new com.kostya.coupons.dto.usersDto.UserDtoForAdmin(u.id, u.userName, u.userType) from UserEntity u"
			+ " where u.id = ?1")
	public UserDtoForAdmin findUserForAdmin(long id);
	
	@Query("select new com.kostya.coupons.dto.usersDto.UserDtoForAdmin(u.id, u.userName, u.userType, c.name) from UserEntity u "
			+ "join CompanyEntity c on u.company.id=c.id")
	public List<UserDtoForAdmin> findAllCompanyUsers();
	
	@Query("select new com.kostya.coupons.dto.usersDto.UserDtoForAdmin(u.id, u.userName, u.userType) from UserEntity u where u.userType!='COMPANY'")
	public List<UserDtoForAdmin> findAllUsers();
	
	@Query("select new com.kostya.coupons.dto.usersDto.UserDtoForAdmin(u.id, u.userName, u.userType) from UserEntity u "
			+ " where u.company.id=?1")
	public List<UserDtoForAdmin> findAllUsersByCompanyId(long companyId);
	
	@Query("select new com.kostya.coupons.dto.usersDto.UserDtoForCompany(u.userName, u.userType) from UserEntity u "
			+ " where u.company.id=?1")
	public List<UserDtoForCompany> findAllUsersInCompany(long companyId);
}
