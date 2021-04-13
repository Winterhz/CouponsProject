package com.kostya.coupons.dto.purchaseDto;

import java.util.Date;

public class PurchaseDtoForAdmin {
	private long id;
	private int amount;
	private Date timeOfPurchase; 
	private String couponName;
	private long price;
	private long userId;
	
	public PurchaseDtoForAdmin(long id, int amount, Date timeOfPurchase, String couponName, long price,
			long userId) {
		super();
		this.id = id;
		this.amount = amount;
		this.timeOfPurchase = timeOfPurchase;
		this.couponName = couponName;
		this.price = price;
		this.userId = userId;
	}
	public PurchaseDtoForAdmin() {
		super();
	}
	
	@Override
	public String toString() {
		return "PurchaseDtoForCompany [id=" + id + ", amount=" + amount + ", timeOfPurchase=" + timeOfPurchase
				+ ", couponName=" + couponName + ", price=" + price + ", userId=" + userId + "]";
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getTimeOfPurchase() {
		return timeOfPurchase;
	}
	public void setTimeOfPurchase(Date timeOfPurchase) {
		this.timeOfPurchase = timeOfPurchase;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
