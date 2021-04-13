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

import com.kostya.coupons.dto.couponDto.CouponDto;
import com.kostya.coupons.dto.couponDto.CouponDtoForUpdate;
import com.kostya.coupons.loginData.UserLoginData;
import com.kostya.coupons.enums.CategoryType;
import com.kostya.coupons.exceptions.ApplicationException;
import com.kostya.coupons.logic.CouponsController;

@RestController
@RequestMapping("/coupons")
public class CouponsApi {

	@Autowired
	private CouponsController couponsController;

	@PostMapping
	public void createCoupon(@RequestBody CouponDto couponDto,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		this.couponsController.createCoupon(couponDto, userLoginData);
	}

	@PutMapping
	public void updateCoupon(@RequestBody CouponDtoForUpdate couponDtoForUpdate,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		this.couponsController.updateCoupon(couponDtoForUpdate, userLoginData);
	}

	@DeleteMapping("/{id}")
	public void deleteCoupon(@PathVariable("id") long id,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		this.couponsController.deleteCoupon(id,  userLoginData);
	}

	@GetMapping("/{id}")
	public CouponDto getCoupon(@PathVariable("id") long id) throws ApplicationException {
		return this.couponsController.getCouponDto(id);
	}

	@GetMapping()
	public List<CouponDto> getAllCoupons() throws ApplicationException {
		return this.couponsController.getAllCouponsDto();
	}
	
	@GetMapping("/couponsByType")
	public List<CouponDto> getCouponsByType(@RequestParam("type") CategoryType type) throws ApplicationException {
		return this.couponsController.getCouponsByType(type);
	}
	
	@GetMapping("/couponsByCompanyId")
	public List<CouponDto> getCouponsByCompanyId(@RequestParam("companyId") long companyId,
			@RequestAttribute("userLoginData") UserLoginData userLoginData) throws ApplicationException {
		return this.couponsController.getCouponsByCompanyId(companyId, userLoginData);
	}
	
	@GetMapping("/couponsByCompanyName")
	public List<CouponDto> getCouponsByCompanyName(@RequestParam("name") String name) throws ApplicationException {
		return this.couponsController.getCouponsByCompanyName(name);
	}
}
