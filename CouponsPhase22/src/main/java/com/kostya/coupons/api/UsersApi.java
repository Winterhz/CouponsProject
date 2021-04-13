package com.kostya.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kostya.coupons.loginData.SuccessfulLoginData;
import com.kostya.coupons.loginData.UserLoginData;
import com.kostya.coupons.loginData.UserLoginRequest;
import com.kostya.coupons.dto.usersDto.UserDto;
import com.kostya.coupons.dto.usersDto.UserDtoForAdmin;
import com.kostya.coupons.dto.usersDto.UserDtoForCompany;
import com.kostya.coupons.dto.usersDto.UserDtoForUpdate;
import com.kostya.coupons.dto.usersDto.UserDtoForUser;
import com.kostya.coupons.enums.UserType;
import com.kostya.coupons.exceptions.ApplicationException;
import com.kostya.coupons.logic.UsersController;

@RestController
@RequestMapping("/users")
public class UsersApi {

	@Autowired
	private UsersController usersController;

	@PostMapping
	public void createUser(@RequestBody UserDto userDto) throws ApplicationException {
		this.usersController.createUser(userDto);
	}
	
	@PostMapping("/registration")
	public void createCustomer(@RequestBody UserDto userDto) throws ApplicationException {
		userDto.setUserType(UserType.CUSTOMER);
		userDto.setCompanyName(null);
		this.usersController.createUser(userDto);
	}

	@PutMapping
	public void updateUser(@RequestBody UserDtoForUpdate userDtoForUpdate,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		this.usersController.updateUser(userDtoForUpdate, userLoginData);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") long id,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException  {
		this.usersController.deleteUser(id, userLoginData);
	}
	
	@GetMapping("/forUser")
	public UserDtoForUser getUserForUser(@RequestParam ("name") String name) throws ApplicationException  {
		return this.usersController.getUserForUser(name);
	}
	
	@GetMapping("/forCompany")
	public UserDtoForCompany getUserForCompany(@RequestParam ("name") String name,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException  {
		return this.usersController.getUserForCompany(name, userLoginData);
	}

	@GetMapping("forAdmin/{id}")
	public UserDtoForAdmin getUserForAdmin(@PathVariable("id") long id,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException  {
		return this.usersController.getUserForAdmin(id,  userLoginData);
	}

	@GetMapping()
	public List<UserDtoForAdmin> getAllUsers(@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException  {
		return this.usersController.getAllUsers(userLoginData);
	}
	
	@GetMapping("/byCompanyId")
	public List<UserDtoForAdmin> getAllUsersByCompanyId(@RequestParam ("companyId") long companyId,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException  {
		return this.usersController.getAllUsersByCompanyId(companyId, userLoginData);
	}
	
	@GetMapping("/usersInCompany")
	public List<UserDtoForCompany> getAllUsersInCompany(@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException  {
		return this.usersController.getAllUsersInCompany(userLoginData);
	}
	
	@PostMapping("/login")
	public SuccessfulLoginData login(@RequestBody UserLoginRequest userLoginRequest) throws ApplicationException {
		return this.usersController.login(userLoginRequest);
	}
	
	@PostMapping("/logout")
	public void login(@RequestBody String token) throws ApplicationException {
		this.usersController.logout(token);
	}
}
