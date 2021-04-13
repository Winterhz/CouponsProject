package com.kostya.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kostya.coupons.loginData.UserLoginData;
import com.kostya.coupons.dto.purchaseDto.PurchaseDto;
import com.kostya.coupons.dto.purchaseDto.PurchaseDtoForAdmin;
import com.kostya.coupons.dto.purchaseDto.PurchaseDtoForCompany;
import com.kostya.coupons.dto.purchaseDto.PurchaseDtoForUser;
import com.kostya.coupons.exceptions.ApplicationException;
import com.kostya.coupons.logic.PurchasesController;

@RestController
@RequestMapping("/purchases")
public class PurchasesApi {

	@Autowired
	private PurchasesController purchaesController;

	@PostMapping
	public void createPurchase(@RequestBody PurchaseDto purchaseDto,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		this.purchaesController.createPurchase(purchaseDto, userLoginData);
	}

	@DeleteMapping("/{id}")
	public void deletePurchase(@PathVariable("id") long id, @RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		this.purchaesController.deletePurchase(id, userLoginData);
	}

	@GetMapping("/userGet/{id}")
	public PurchaseDtoForUser getPurchaseForUser(@PathVariable("id") long id,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.purchaesController.getPurchaseForUser(id, userLoginData);
	}
	
	@GetMapping("/companyGet/{id}")
	public PurchaseDtoForCompany getPurchaseForCompany(@PathVariable("id") long id,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.purchaesController.getPurchaseForCompany(id, userLoginData);
	}
	
	@GetMapping("/adminGet/{id}")
	public PurchaseDtoForAdmin getPurchaseForAdmin(@PathVariable("id") long id,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.purchaesController.getPurchaseForAdmin(id, userLoginData);
	}

	@GetMapping("/userGetAll")
	public List<PurchaseDtoForUser> getAllPurchasesForUser(@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.purchaesController.getAllPurchasesForUser(userLoginData);
	}
	
	@GetMapping("/companyGetAll")
	public List<PurchaseDtoForCompany> getAllPurchasesForCompany(@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.purchaesController.getAllPurchasesForCompany(userLoginData);
	}
	
	@GetMapping("/adminGetAll")
	public List<PurchaseDtoForAdmin> getAllPurchasesForAdmin(@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.purchaesController.getAllPurchasesForAdmin(userLoginData);
	}
	
	@GetMapping("/purchasesByUserId")
	public List<PurchaseDto> getPurchasesByUserId(@RequestParam("userId") long userId,
	        @RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.purchaesController.getPurchasesByUserId(userId, userLoginData);
	}
	
	@GetMapping("/purchasesByCompanyId")
	public List<PurchaseDto> getPurchasesByCompanyId(@RequestParam("companyId") long companyId,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.purchaesController.getPurchasesByCompanyId(companyId, userLoginData);
	}
	
	@GetMapping("/couponsByMaxPriceForUser")
	public List<PurchaseDtoForUser> getPurchasedCouponsByMaxPriceForUser(@RequestParam("maxPrice") long maxPrice,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.purchaesController.getPurchasedCouponsByMaxPriceForUser(maxPrice, userLoginData);
	}
	
	@GetMapping("/couponsByMaxPriceForCompany")
	public List<PurchaseDtoForCompany> getPurchasedCouponsByMaxPriceForCompany(@RequestParam("maxPrice") long maxPrice,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.purchaesController.getPurchasedCouponsByMaxPriceForCompany(maxPrice, userLoginData);
	}
	
	@GetMapping("/couponsByMaxPriceForAdmin")
	public List<PurchaseDtoForAdmin> getPurchasedCouponsByMaxPriceForAdmin(@RequestParam("maxPrice") long maxPrice,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.purchaesController.getPurchasedCouponsByMaxPriceForAdmin(maxPrice, userLoginData);
	}
}
