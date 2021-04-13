package com.kostya.coupons.logic;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kostya.coupons.dao.ICouponsDao;
import com.kostya.coupons.dto.couponDto.CouponDto;
import com.kostya.coupons.dto.couponDto.CouponDtoForUpdate;
import com.kostya.coupons.loginData.UserLoginData;
import com.kostya.coupons.entities.CouponEntity;
import com.kostya.coupons.enums.CategoryType;
import com.kostya.coupons.enums.ErrorType;
import com.kostya.coupons.enums.UserType;
import com.kostya.coupons.exceptions.ApplicationException;

@Controller
public class CouponsController {

	@Autowired
	private ICouponsDao couponsDao;

	@Autowired
	private CompaniesController companiesController;

	public void createCoupon(CouponDto couponDto, UserLoginData userLoginData) throws ApplicationException {
		CouponEntity couponEntity = new CouponEntity(couponDto);
		try {
			couponEntity.setCompany(this.companiesController.getCompanyById(userLoginData.getCompanyId()));
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while trying to get company by id");
		}
		try {
			if(couponsDao.existsByName(couponEntity.getName())) {
				throw new ApplicationException(ErrorType.IS_EXISTS_ERROR, "Coupon name already exists");
			}
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while checking if coupon exists by name");
		}
		
		validateCreateCoupon(couponEntity, userLoginData);
		couponEntity.setCompany(this.companiesController.getCompanyById(userLoginData.getCompanyId()));
		try {
			this.couponsDao.save(couponEntity);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while trying to create a coupon");
		}
	}

	public void updateCoupon(CouponDtoForUpdate couponDtoForUpdate, UserLoginData userLoginData) throws ApplicationException {
		CouponEntity couponEntity = new CouponEntity(couponDtoForUpdate);
		try {
			couponEntity.setCompany(this.companiesController.getCompanyById(userLoginData.getCompanyId()));
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while trying to get company by id");
		}
		validateUpdateCoupon(couponEntity, userLoginData);
		try {
			this.couponsDao.save(couponEntity);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error while try to update coupon");
		}
	}

	private void validateUpdateCoupon(CouponEntity couponEntity, UserLoginData userLoginData) throws ApplicationException{
		if(userLoginData.getUserType()!=UserType.COMPANY) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only users of type company can update coupons");
		}
		if(couponEntity.getCompany().getId()!=userLoginData.getCompanyId()) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only users of exact company can update coupons");
		}
		if(couponEntity.getPrice()<0) {
			throw new ApplicationException(ErrorType.PRICE_ERROR, "Coupon price can not be negative number");
		}
		if(couponEntity.getAmount()<0) {
			throw new ApplicationException(ErrorType.AMOUNT_ERROR, "Coupons amount can not be negative number");
		}
		Date today = Date.valueOf(LocalDate.now());
		Date couponEndDay =  new Date(couponEntity.getEndDate().getTime());
		if(today.after(couponEndDay)) {
			throw new ApplicationException(ErrorType.OUTDATED_COUPON_ERROR, "Coupon is outdated(coupons end date already passed).");
		}
	}

	private void validateCreateCoupon(CouponEntity couponEntity, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.COMPANY) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only users of type company can create coupons");
		}
		if(couponEntity.getName()==null) {
			throw new ApplicationException(ErrorType.NULL_NAME_ERROR, "Null coupon name");
		}
		if(couponEntity.getName().length()>46) {
			throw new ApplicationException(ErrorType.LENGTH_ERROR, "Coupon name length need to be 45 or less");
		}
		if(couponEntity.getPrice()<0) {
			throw new ApplicationException(ErrorType.PRICE_ERROR, "Coupon price can not be negative number");
		}
		if(couponEntity.getAmount()<0) {
			throw new ApplicationException(ErrorType.AMOUNT_ERROR, "Coupons amount can not be negative number");
		}
		Date today = Date.valueOf(LocalDate.now());
		Date couponEndDay =  new Date(couponEntity.getEndDate().getTime());
		if(today.after(couponEndDay)) {
			throw new ApplicationException(ErrorType.OUTDATED_COUPON_ERROR, "Coupon is outdated(coupons end date already passed).");
		}
	}

	public void deleteCoupon(long id, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()==UserType.CUSTOMER) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Customers can't delete coupons");
		}
		//check if Company user has the same companyId as coupon that he try to delete
		CouponEntity couponEntity;
		try {
		couponEntity = getCoupon(id);
		}  catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while trying to get coupon by id");
		}
		if(userLoginData.getUserType()==UserType.COMPANY) {
			if(userLoginData.getCompanyId()!=couponEntity.getCompany().getId()) {
				throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Company users can only delete coupons of their company");
			}
		}
		try {
			this.couponsDao.deleteById(id);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error while try to delete coupon");
		}
	}

	public CouponEntity getCoupon(long id) throws ApplicationException {
		try {
			return this.couponsDao.findById(id).get();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error while try to get coupon");
		}
	}

	public CouponDto getCouponDto(long id) throws ApplicationException {
		try {
			return this.couponsDao.getCouponDto(id);
		}catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error while try to get couponDto");
		}
	}

	public List<CouponDto> getAllCouponsDto() throws ApplicationException {
		try {
			return this.couponsDao.getAllCouponsDto();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error while try to get all couponsDto");
		}
	}

	public List<CouponDto> getCouponsByType(CategoryType categoryType) throws ApplicationException {
		try {
			return this.couponsDao.findCouponsByType(categoryType);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error while try to get couponsDto by category type");
		}
	}

	public List<CouponDto> getCouponsByCompanyId(long companyId, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only admin can get coupons by company id");
		}
		try {
			return this.couponsDao.findCouponsByCompanyId(companyId);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error while try to get couponsDto by company id");
		}
	}

	public List<CouponDto> getCouponsByCompanyName(String name) throws ApplicationException {
		try {
			return this.couponsDao.findCouponsByCompanyName(name);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error while try to get couponsDto by company name");
		}
	}

	public CouponEntity getByNameForPurchase(String name) throws ApplicationException {
		try {
			return this.couponsDao.findByName(name);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error while try to get coupons by name");
		}
	}

	public void updateCouponForPurchases(long id, int amount) throws ApplicationException {
		try {
			this.couponsDao.updateCouponForPurchases(id, amount);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "error while try to update amount of coupons after purchases");
		}
	}
}
