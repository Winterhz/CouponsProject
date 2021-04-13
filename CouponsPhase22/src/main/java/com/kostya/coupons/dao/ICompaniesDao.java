package com.kostya.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kostya.coupons.dto.companyDto.CompanyDto;
import com.kostya.coupons.dto.companyDto.CompanyDtoForAdmin;
import com.kostya.coupons.entities.CompanyEntity;


public interface ICompaniesDao extends CrudRepository<CompanyEntity, Long> {
	
	public CompanyEntity findByName(String name);
	
	public boolean existsByName(String name);
	
	@Query("select new com.kostya.coupons.dto.companyDto.CompanyDto(c.name, c.address, c.phoneNumber) from CompanyEntity c where c.name = ?1")
	public CompanyDto getCompany(String name);
	
	@Query("select new com.kostya.coupons.dto.companyDto.CompanyDtoForAdmin(c.id, c.name, c.address, c.phoneNumber) from CompanyEntity c where c.id = ?1")
	public CompanyDtoForAdmin getCompanyForAdmin(long id);
	
	@Query("select new com.kostya.coupons.dto.companyDto.CompanyDto(c.name, c.address, c.phoneNumber) from CompanyEntity c")
	public List<CompanyDto> getAllCompanies();
	
	@Query("select new com.kostya.coupons.dto.companyDto.CompanyDtoForAdmin(c.id, c.name, c.address, c.phoneNumber) from CompanyEntity c")
	public List<CompanyDtoForAdmin> getAllCompaniesForAdmin();
}
