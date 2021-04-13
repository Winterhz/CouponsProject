package com.kostya.coupons.dto.purchaseDto;

import java.util.Date;

import com.kostya.coupons.dto.couponDto.CouponDto;
import com.kostya.coupons.entities.PurchaseEntity;

public class PurchaseDto {
	private long id;
	private int amount;
	private Date timeStamp; 
	private String couponName;
	
	public PurchaseDto(PurchaseEntity purchaseEntity) {
		super();
		this.amount = purchaseEntity.getAmount();
		this.timeStamp = purchaseEntity.getTimeStamp();
		CouponDto couponDto = new CouponDto(purchaseEntity.getCoupon());
		this.couponName = couponDto.getName(); 
	}
	public PurchaseDto() {
		super();
	}
	
	@Override
	public String toString() {
		return "PurchaseDto [id=" + id + ", amount=" + amount + ", timeStamp=" + timeStamp + ", couponName="
				+ couponName + "]";
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
