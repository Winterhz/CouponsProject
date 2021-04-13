package com.kostya.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.kostya.coupons.dto.purchaseDto.PurchaseDto;
import com.kostya.coupons.dto.purchaseDto.PurchaseDtoForAdmin;
import com.kostya.coupons.dto.purchaseDto.PurchaseDtoForCompany;
import com.kostya.coupons.dto.purchaseDto.PurchaseDtoForUser;
import com.kostya.coupons.entities.PurchaseEntity;

public interface IPurchasesDao extends CrudRepository<PurchaseEntity, Long>{
	
	@Query("select new com.kostya.coupons.dto.purchaseDto.PurchaseDto(p) from PurchaseEntity p where p.user.id = ?1")
	public List<PurchaseDto> findPurchasesByUserId(long userId);
	
	@Query("select new com.kostya.coupons.dto.purchaseDto.PurchaseDto(p) from PurchaseEntity p where p.coupon.company.id = ?1")
	public List<PurchaseDto> findPurchasesByCompanyId(long companyId);

	//maybe in findPurchasedCouponsByMaxPrice better to change dto to couponDto, but actually purchaseDto have all details of coupon that user need
	//and also id of purchase and time stamp which is good, because user may want to know when he bought this unreal expensive coupon
	@Query("select new com.kostya.coupons.dto.purchaseDto.PurchaseDtoForUser(p.id, p.amount, p.timeStamp, c.name, c.price, c.description, c.startDate,"
			+ "c.endDate, c.image, c.company.name) from PurchaseEntity p join CouponEntity c on p.coupon.id=c.id"
			+ " where p.user.id=?1 and c.price<=?2")
	public List<PurchaseDtoForUser> findPurchasedCouponsByMaxPriceForUser(long userId, long maxPrice);
	
	@Query("select new com.kostya.coupons.dto.purchaseDto.PurchaseDtoForCompany(p.id, p.amount, p.timeStamp, c.name, c.price, u.userName) from PurchaseEntity"
			+ " p join CouponEntity c on p.coupon.id=c.id join UserEntity u on p.user.id=u.id where c.company.id=?1 and c.price<=?2")
	public List<PurchaseDtoForCompany> findPurchasedCouponsByMaxPriceForCompany(long companyId, long maxPrice);
	
	@Query("select new com.kostya.coupons.dto.purchaseDto.PurchaseDtoForAdmin(p.id, p.amount, p.timeStamp, c.name, c.price, u.id) from PurchaseEntity"
			+ " p join CouponEntity c on p.coupon.id=c.id join UserEntity u on p.user.id=u.id where c.price<=?1")
	public List<PurchaseDtoForAdmin> findPurchasedCouponsByMaxPriceForAdmin(long maxPrice);
	
	@Query("select new com.kostya.coupons.dto.purchaseDto.PurchaseDtoForUser(p.id, p.amount, p.timeStamp, c.name, c.price, c.description, c.startDate,\r\n" + 
			"	c.endDate, c.image, c.company.name) from PurchaseEntity p join CouponEntity c on p.coupon.id=c.id where p.id=?1 and p.user.id=?2")
	public PurchaseDtoForUser findPurchaseForUser(long id, long userId);
	
	@Query("select new com.kostya.coupons.dto.purchaseDto.PurchaseDtoForCompany(p.id, p.amount, p.timeStamp, c.name, c.price, u.userName) \r\n" + 
			"from PurchaseEntity p join CouponEntity c on p.coupon.id=c.id join UserEntity u on p.user.id=u.id where p.id=?1 and p.coupon.company.id=?2")
	public PurchaseDtoForCompany findPurchaseForCompany(long id, long companyId);
	
	@Query("select new com.kostya.coupons.dto.purchaseDto.PurchaseDtoForAdmin(p.id, p.amount, p.timeStamp, c.name, c.price, u.id) \r\n" + 
			"from PurchaseEntity p join CouponEntity c on p.coupon.id=c.id join UserEntity u on p.user.id=u.id where p.id=?1")
	public PurchaseDtoForAdmin findPurchaseForAdmin(long id);
	
	@Query("select new com.kostya.coupons.dto.purchaseDto.PurchaseDtoForUser(p.id, p.amount, p.timeStamp, c.name, c.price, c.description, c.startDate,\r\n" + 
			"	c.endDate, c.image, c.company.name) from PurchaseEntity p join CouponEntity c on p.coupon.id=c.id where p.user.id=?1")
	public List<PurchaseDtoForUser> findAllPurchasesForUser(long userId);
	
	@Query("select new com.kostya.coupons.dto.purchaseDto.PurchaseDtoForCompany(p.id, p.amount, p.timeStamp, c.name, c.price, u.userName) \r\n" + 
			"	from PurchaseEntity p join CouponEntity c on p.coupon.id=c.id join UserEntity u on p.user.id=u.id where p.coupon.company.id=?1")
	public List<PurchaseDtoForCompany> findAllPurchasesForCompany(long companyId);
	
	@Query("select new com.kostya.coupons.dto.purchaseDto.PurchaseDtoForAdmin(p.id, p.amount, p.timeStamp, c.name, c.price, u.id) \r\n" + 
			"	from PurchaseEntity p join CouponEntity c on p.coupon.id=c.id join UserEntity u on p.user.id=u.id")
	public List<PurchaseDtoForAdmin> findAllPurchasesForAdmin();
}
