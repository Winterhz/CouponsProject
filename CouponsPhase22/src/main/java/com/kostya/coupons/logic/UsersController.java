package com.kostya.coupons.logic;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kostya.coupons.dao.IUsersDao;
import com.kostya.coupons.loginData.SuccessfulLoginData;
import com.kostya.coupons.loginData.UserLoginData;
import com.kostya.coupons.loginData.UserLoginRequest;
import com.kostya.coupons.dto.usersDto.UserDto;
import com.kostya.coupons.dto.usersDto.UserDtoForAdmin;
import com.kostya.coupons.dto.usersDto.UserDtoForCompany;
import com.kostya.coupons.dto.usersDto.UserDtoForUpdate;
import com.kostya.coupons.dto.usersDto.UserDtoForUser;
import com.kostya.coupons.entities.CompanyEntity;
import com.kostya.coupons.entities.UserEntity;
import com.kostya.coupons.enums.ErrorType;
import com.kostya.coupons.enums.UserType;
import com.kostya.coupons.exceptions.ApplicationException;

@Controller
public class UsersController {
	private final String SALT = "2AfJHGl64ljfI5F";

	@Autowired
	private IUsersDao usersDao;

	@Autowired
	private CompaniesController companiesController;

	@Autowired
	private CacheController cacheController;

	public void createUser(UserDto userDto) throws ApplicationException {
		UserEntity userEntity = new UserEntity(userDto);
		boolean isExists;
		try {
			isExists = this.usersDao.existsByUserName(userEntity.getUserName());
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try check if user exists by name");
		}
		if(isExists == true) {
			throw new ApplicationException(ErrorType.IS_EXISTS_ERROR, "Username already exists");
		}
		validateUser(userEntity);
		userEntity.setPassword(userEntity.getPassword().hashCode()+SALT);
		try {
			userEntity.setCompany(companiesController.getCompanyByName(userDto.getCompanyName()));
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try to get company by name");
		}
		if(userEntity.getUserType()==null) {
			userEntity.setUserType(UserType.CUSTOMER);
		}
		try {
			this.usersDao.save(userEntity);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error while try to create a new user");
		}
	}

	public void updateUser(UserDtoForUpdate userDtoForUpdate, UserLoginData userLoginData) throws ApplicationException {
		//checking if user wants to change his name or not. because if i give an opportunity for users to change user name
		//then i need to make this checks because if i wont i will get that user name already exists every time user just want to update smth else
		UserEntity user = new UserEntity(userDtoForUpdate);
		String newName = user.getUserName();
		long id = user.getId();
		UserEntity userBeforeChange;
		try {
			userBeforeChange = getById(id);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try to get user by id");
		}
		String previousName = userBeforeChange.getUserName();
		if(!(newName.equals(previousName))) {
			if (this.usersDao.existsByUserName(user.getUserName())) {
				throw new ApplicationException(ErrorType.IS_EXISTS_ERROR, "user name already exists");
			}
		}
		user.setId(userLoginData.getUserId());
		validateUser(user);
		user.setPassword(user.getPassword().hashCode()+SALT);
		try {
			this.usersDao.save(user);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error while try to update user");
		}
	}

	private void validateUser(UserEntity user) throws ApplicationException {
		if(user.getUserName()==null) {
			throw new ApplicationException(ErrorType.NULL_NAME_ERROR, "Null user name");
		}
		if(user.getUserName().length()<3) {
			throw new ApplicationException(ErrorType.LENGTH_ERROR, "User name length is too short. Min length is 3 symbols");
		}
		if(user.getUserName().length()>24) {
			throw new ApplicationException(ErrorType.LENGTH_ERROR, "User name length is too long. Max length is 24 symbols");
		}
		if(user.getPassword()==null) {
			throw new ApplicationException(ErrorType.NULL_NAME_ERROR, "Null user password");
		}
		if(user.getPassword().length()<6) {
			throw new ApplicationException(ErrorType.LENGTH_ERROR, "User password length is too short. Min length is 6 symbols");
		}
		if(user.getPassword().length()>18) {
			throw new ApplicationException(ErrorType.LENGTH_ERROR, "User password length is too long. Max length is 24 symbols");
		}

	}

	public void deleteUser(long id, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only admin can delete users");
		}
		try {
			this.usersDao.deleteById(id);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to delete user");
		}
	}

	public UserEntity getById(long id) throws ApplicationException {
		try {
			return this.usersDao.findById(id).get();
		} catch (Exception e) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR,"Error while try to get user by id");
		}
	}

	public UserDtoForUser getUserForUser(String name) throws ApplicationException {
		try {
			return this.usersDao.findUserForUser(name);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get userDto for user");
		}
	}

	public UserDtoForCompany getUserForCompany(String name, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.COMPANY) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only Company user can get this user details");
		}
		try {
			return this.usersDao.findUserForCompany(name);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get userDto for company");
		}
	}

	public UserDtoForAdmin getUserForAdmin(long id, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only Admin can get all user details");
		}
		try {
			return this.usersDao.findUserForAdmin(id);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get userDto for admin");
		}
	}

	public List<UserDtoForAdmin> getAllUsers(UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only Admin can get all users details");
		}
		List<UserDtoForAdmin> list = this.usersDao.findAllUsers();
		List<UserDtoForAdmin> listCompany = this.usersDao.findAllCompanyUsers();
		list.addAll(listCompany);
		try {
			
			return list;
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get userDto for admin");
		}
	}

	public List<UserDtoForAdmin> getAllUsersByCompanyId(long companyId, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only admin can get users by company id");
		}
		try {
			return this.usersDao.findAllUsersByCompanyId(companyId);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get userDto for admin");
		}
	}

	public List<UserDtoForCompany> getAllUsersInCompany(UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.COMPANY) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only company users can get users in their company");
		}
		try {
			return this.usersDao.findAllUsersInCompany(userLoginData.getCompanyId());
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get userDto for admin");
		}
	}

	public SuccessfulLoginData login(UserLoginRequest userLoginRequest) throws ApplicationException {
		int passwordInt = userLoginRequest.getPassword().hashCode();
		String password = String.valueOf(passwordInt)+SALT;
		userLoginRequest.setPassword(password);
		UserLoginData userLoginData;
		try {
			userLoginData = this.usersDao.login(userLoginRequest.getUserName(), userLoginRequest.getPassword());
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to login");
		}
		if(userLoginData == null) {
			throw new ApplicationException(ErrorType.LOGIN_ERROR,"null user login data");
		}
		if(userLoginData.getUserType()==UserType.COMPANY) {
			userLoginData.setCompanyId(this.usersDao.getCompanyIdByUserId(userLoginData.getUserId()));
		}
		SuccessfulLoginData successfulLoginData;
		String token = generateToken(userLoginRequest);
		cacheController.put(token, userLoginData);
		UserEntity user = usersDao.findById(userLoginData.getUserId()).get();
		if(user.getUserType()==UserType.COMPANY) {
			CompanyEntity company = companiesController.getCompanyById(user.getCompany().getId());
			String companyName = company.getName();
			successfulLoginData = new SuccessfulLoginData(userLoginData.getUserType(), token,
					user.getId(), user.getUserName(), companyName, user.getPassword());
		} else {
			successfulLoginData = new SuccessfulLoginData(userLoginData.getUserType(), token, user.getId(), user.getUserName());
		}
		return successfulLoginData;
	}
	private	String generateToken(UserLoginRequest userLoginRequest) {
		int token = (userLoginRequest.getUserName() + Calendar.getInstance().getTime().toString()+"ASDASDASD").hashCode();
		return String.valueOf(token);
	}
	
	public void logout(String token) {
		cacheController.delete(token);
	}
}
