package com.kostya.coupons.logic;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kostya.coupons.dao.IPurchasesDao;
import com.kostya.coupons.loginData.UserLoginData;
import com.kostya.coupons.dto.purchaseDto.PurchaseDto;
import com.kostya.coupons.dto.purchaseDto.PurchaseDtoForAdmin;
import com.kostya.coupons.dto.purchaseDto.PurchaseDtoForCompany;
import com.kostya.coupons.dto.purchaseDto.PurchaseDtoForUser;
import com.kostya.coupons.entities.PurchaseEntity;
import com.kostya.coupons.enums.ErrorType;
import com.kostya.coupons.enums.UserType;
import com.kostya.coupons.exceptions.ApplicationException;

@Controller
public class PurchasesController {

	@Autowired
	private IPurchasesDao purchasesDao;

	@Autowired
	private CouponsController couponsController; 

	@Autowired
	private UsersController usersController;

	public void createPurchase(PurchaseDto purchaseDto, UserLoginData userLoginData)  throws ApplicationException{
		PurchaseEntity purchaseEntity = new PurchaseEntity(purchaseDto);
		Date date = new Date(System.currentTimeMillis());
		purchaseEntity.setTimeStamp(date);
		try {
		purchaseEntity.setUser(this.usersController.getById(userLoginData.getUserId()));
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try to get company by id");
		}
		try {
		purchaseEntity.setCoupon(this.couponsController.getByNameForPurchase(purchaseDto.getCouponName()));
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try to get coupon by name");
		}
		validatePurchase(purchaseEntity, userLoginData);
		try {
			this.couponsController.updateCouponForPurchases(purchaseEntity.getCoupon().getId(), purchaseEntity.getAmount());
			this.purchasesDao.save(purchaseEntity);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try to create purchase");
		}
	}

	public void validatePurchase(PurchaseEntity purchase, UserLoginData userLoginData) throws ApplicationException{
		// make sure user that want to purchase coupon is UserType.CUSTOMER
		if(userLoginData.getUserType()!=UserType.CUSTOMER) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only Customers can purchase coupons");		
		}
		// checking if coupon is available, if not available throws exception
		// actually do not have to write this check, because of the next check
		// but i want to show if there is no coupons at all, or someone just tries to buy more then.....
		// amount in the store to show that customer still can buy the amount that is available in the store
		if(purchase.getCoupon().getAmount()<1) {
			throw new ApplicationException(ErrorType.IS_AVAILABLE_ERROR, "Coupon is not available in the store");
		}
		// checking if there is enough coupons in the store, if not throws exception
		int amountOfAvailableCoupons = purchase.getCoupon().getAmount();
		int amountOfCouponsInPurchase = purchase.getAmount();

		if(amountOfAvailableCoupons<amountOfCouponsInPurchase) {
			throw new ApplicationException(ErrorType.AMOUNT_ERROR, "Not enough coupons in the store");
		}
	}

	public void deletePurchase(long id, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.INVALID_USER_TYPE, "Only admin can delete purchases");
		}
		try {
			this.purchasesDao.deleteById(id);
		} catch(Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try to delete purchase");
		}
	}

	public PurchaseDtoForUser getPurchaseForUser(long id, UserLoginData userLoginData) throws ApplicationException {
		try {
			return this.purchasesDao.findPurchaseForUser(id, userLoginData.getUserId());
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try to get purchase");
		}
	}

	public PurchaseDtoForCompany getPurchaseForCompany(long id, UserLoginData userLoginData) throws ApplicationException {
		try {
			 return this.purchasesDao.findPurchaseForCompany(id, userLoginData.getCompanyId());
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try to get purchase");
		}
	}

	public PurchaseDtoForAdmin getPurchaseForAdmin(long id, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Only admin can get any purchase");
		}
		try {
			return this.purchasesDao.findPurchaseForAdmin(id);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try to get purchase");
		}
	}

	public List<PurchaseDtoForUser> getAllPurchasesForUser(UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.CUSTOMER) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Only customer can get his purchases");
		}
		try {
			return this.purchasesDao.findAllPurchasesForUser(userLoginData.getUserId());
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get purchases for user");
		}
	}

	public List<PurchaseDtoForCompany> getAllPurchasesForCompany(UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.COMPANY) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Only company user can get company purchases");
		}
		try {
			return this.purchasesDao.findAllPurchasesForCompany(userLoginData.getCompanyId());
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get purchases for user");
		}
	}

	public List<PurchaseDtoForAdmin> getAllPurchasesForAdmin(UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Only admin can get all purchases");
		}
		try {
			return this.purchasesDao.findAllPurchasesForAdmin();
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get purchases for user");
		}
	}

	public List<PurchaseDto> getPurchasesByUserId(long userId, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Only admin can get any purchase");
		}
		try {
			return this.purchasesDao.findPurchasesByUserId(userId);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get purchases by user id");
		}
	}

	public List<PurchaseDto> getPurchasesByCompanyId(long companyId, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Only admin can get any purchase");
		}
		try {
			return this.purchasesDao.findPurchasesByCompanyId(companyId);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error while try to get purchases by company id");
		}
	}

	public List<PurchaseDtoForUser> getPurchasedCouponsByMaxPriceForUser(long maxPrice, UserLoginData userLoginData) throws ApplicationException {
		try {
			return this.purchasesDao.findPurchasedCouponsByMaxPriceForUser(userLoginData.getUserId(), maxPrice);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try to get purchases by max price for user");
		}
	}

	public List<PurchaseDtoForCompany> getPurchasedCouponsByMaxPriceForCompany(long maxPrice, UserLoginData userLoginData) throws ApplicationException {
		try {
			return this.purchasesDao.findPurchasedCouponsByMaxPriceForCompany(userLoginData.getCompanyId(), maxPrice);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try to get purchases by max price for company");
		}
	}

	public List<PurchaseDtoForAdmin> getPurchasedCouponsByMaxPriceForAdmin(long maxPrice, UserLoginData userLoginData) throws ApplicationException {
		if(userLoginData.getUserType()!=UserType.ADMIN) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Only admin can get any purchase by max price");
		}
		try {
			return this.purchasesDao.findPurchasedCouponsByMaxPriceForAdmin(maxPrice);
		} catch (Exception e) {
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "Error when try to get purchases by max price for user");
		}
	}
}
