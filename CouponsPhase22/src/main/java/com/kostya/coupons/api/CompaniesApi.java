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

import com.kostya.coupons.dto.companyDto.CompanyDto;
import com.kostya.coupons.dto.companyDto.CompanyDtoForAdmin;
import com.kostya.coupons.dto.companyDto.CompanyDtoForUpdate;
import com.kostya.coupons.loginData.UserLoginData;
import com.kostya.coupons.exceptions.ApplicationException;
import com.kostya.coupons.logic.CompaniesController;

@RestController
@RequestMapping("/companies")
public class CompaniesApi {

	@Autowired
	private CompaniesController companiesController;

	@PostMapping
	public void createCompany(@RequestBody CompanyDto companyDto,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		this.companiesController.createCompany(companyDto, userLoginData);
	}

	@PutMapping
	public void updateCompany(@RequestBody CompanyDtoForUpdate companyDtoForUpdate,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		System.out.println(companyDtoForUpdate.getName());
		this.companiesController.updateCompany(companyDtoForUpdate, userLoginData);
	}

	@DeleteMapping("/{id}")
	public void deleteCompany(@PathVariable("id") long id,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		this.companiesController.deleteCompany(id, userLoginData);
	}

	@GetMapping("/byName")
	public CompanyDto getCompany(@RequestParam ("name") String name) throws ApplicationException {
		return this.companiesController.getCompany(name);
	}
	
	@GetMapping("/{id}")
	public CompanyDtoForAdmin getCompanyForAdmin(@PathVariable("id") long id,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.companiesController.getCompanyForAdmin(id, userLoginData);
	}

	@GetMapping()
	public List<CompanyDto> getAllCompanies() throws ApplicationException {
		return this.companiesController.getAllCompanies();
	}
	
	@GetMapping("/forAdmin")
	public List<CompanyDtoForAdmin> getAllCompaniesForAdmin(@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.companiesController.getAllCompaniesForAdmin(userLoginData);
	}
}
