package com.kostya.coupons.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kostya.coupons.dto.couponDto.CouponDto;
import com.kostya.coupons.entities.CouponEntity;
import com.kostya.coupons.enums.CategoryType;

public interface ICouponsDao extends CrudRepository<CouponEntity, Long> {

	public CouponEntity findByName(String name);

	public boolean existsByName(String name);
	
	@Transactional
	@Modifying
	@Query("Update CouponEntity c set c.amount=c.amount-?2 where c.id=?1")
	public void updateCouponForPurchases(long id, int amount);
	
	@Query("select new com.kostya.coupons.dto.couponDto.CouponDto(c.id, c.name, c.price, c.description, c.startDate, c.endDate, c.categoryType,"
			+ " c.amount, c.image, c.company.name)"
			+ " from CouponEntity c where c.company.id = ?1")
	public List<CouponDto> findCouponsByCompanyId(long companyId);

	@Query("select new com.kostya.coupons.dto.couponDto.CouponDto(c.id, c.name, c.price, c.description, c.startDate, c.endDate, c.categoryType,\r\n" + 
			"		 c.amount, c.image, c.company.name) from CouponEntity c where c.categoryType = ?1")
	public List<CouponDto> findCouponsByType(CategoryType categoryType);
	
	@Query("select new com.kostya.coupons.dto.couponDto.CouponDto(c.id, c.name, c.price, c.description, c.startDate, c.endDate,\r\n" + 
			"			c.categoryType, c.amount, c.image, c.company.name) from CouponEntity c where c.id = ?1")
	public CouponDto getCouponDto(long id);
	
	@Query("select new com.kostya.coupons.dto.couponDto.CouponDto(c.id, c.name, c.price, c.description, c.startDate, c.endDate,\r\n" + 
			"			c.categoryType, c.amount, c.image, c.company.name) from CouponEntity c")
	public List<CouponDto> getAllCouponsDto();
	
	@Query("select new com.kostya.coupons.dto.couponDto.CouponDto(c.id, c.name, c.price, c.description, c.startDate, c.endDate, c.categoryType, " + 
			"		    c.amount, c.image, c.company.name) from CouponEntity c where c.company.name = ?1")
	public List<CouponDto> findCouponsByCompanyName(String name);
	
	@Transactional
	@Modifying
	@Query("Delete from CouponEntity c where c.endDate<=CURRENT_DATE")
	public void deleteOutdatedCoupons();
}
