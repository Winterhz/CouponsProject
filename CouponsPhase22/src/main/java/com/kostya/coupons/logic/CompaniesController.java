package com.kostya.coupons.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kostya.coupons.dao.ICompaniesDao;
import com.kostya.coupons.dto.companyDto.CompanyDto;
import com.kostya.coupons.dto.companyDto.CompanyDtoForAdmin;
import com.kostya.coupons.dto.companyDto.CompanyDtoForUpdate;
import com.kostya.coupons.loginData.UserLoginData;
import com.kostya.coupons.entities.CompanyEntity;
import com.kostya.coupons.enums.ErrorType;
import com.kostya.coupons.enums.UserType;
import com.kostya.coupons.exceptions.ApplicationException;

@Controller
public class CompaniesController {

	@Autowired
	private ICompaniesDao companiesDao;

	public void createCompany(CompanyDto companyDto, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only admin can create and update company");
		}
		CompanyEntity companyEntity = new CompanyEntity(companyDto);
		if(this.companiesDao.existsByName(companyEntity.getName())) {
			throw new ApplicationException(ErrorType.IS_EXISTS_ERROR, "Company name already exists");
		}
		try {
			this.companiesDao.save(companyEntity);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to create a company");
		}
	}

	public void updateCompany(CompanyDtoForUpdate companyDtoForUpdate, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only admin can create and update company");
		}
		CompanyEntity companyEntity = new CompanyEntity(companyDtoForUpdate);
		try {
			this.companiesDao.save(companyEntity);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to update a company");
		}
	}

	public void deleteCompany(long id, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only admin can delete company");
		}
		try {
			this.companiesDao.deleteById(id);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to delete a company");
		}
	}

	public CompanyDto getCompany(String name) throws ApplicationException {
		try {
			return this.companiesDao.getCompany(name);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get a company");
		}
	}

	public CompanyDtoForAdmin getCompanyForAdmin(long id, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only admin can get company by id");
		}
		try {
			return this.companiesDao.getCompanyForAdmin(id);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get a company");
		}
	}

	public List<CompanyDto> getAllCompanies() throws ApplicationException {
		try {
			return this.companiesDao.getAllCompanies();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get all companies");
		}
	}

	public List<CompanyDtoForAdmin> getAllCompaniesForAdmin(UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only admin can get all companiesDto with id");
		}
		try {
			return this.companiesDao.getAllCompaniesForAdmin();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get all companies for admin");
		}
	}

	public CompanyEntity getCompanyById(long id) throws ApplicationException {
		try {
			return this.companiesDao.findById(id).get();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get company by id");
		}
	}

	public CompanyEntity getCompanyByName(String name) throws ApplicationException {
		try {
			return this.companiesDao.findByName(name);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get company by name");
		}
	}

}
