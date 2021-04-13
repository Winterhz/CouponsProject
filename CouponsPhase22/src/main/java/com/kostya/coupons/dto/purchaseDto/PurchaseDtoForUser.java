package com.kostya.coupons.dto.purchaseDto;

import java.util.Date;

public class PurchaseDtoForUser {
	private long id;
	private int amount;
	private Date timeOfPurchase; 
	private String couponName;
	private long price;
	private String description;
	private Date startDate;
	private Date endDate;
	private String image;
	private String companyName;
	public PurchaseDtoForUser(long id, int amount, Date timeOfPurchase, String couponName, long price, String description,
			Date startDate, Date endDate, String image, String companyName) {
		super();
		this.id = id;
		this.amount = amount;
		this.timeOfPurchase = timeOfPurchase;
		this.couponName = couponName;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.image = image;
		this.companyName = companyName;
	}
	public PurchaseDtoForUser() {
		super();
	}
	
	@Override
	public String toString() {
		return "PurchaseDtoForUser [id=" + id + ", amount=" + amount + ", timeOfPurchase=" + timeOfPurchase + ", couponName=" + couponName
				+ ", price=" + price + ", description=" + description + ", startDate=" + startDate + ", endDate="
				+ endDate + ", image=" + image + ", companyName=" + companyName + "]";
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
	public String getcouponName() {
		return couponName;
	}
	public void setcouponName(String couponName) {
		this.couponName = couponName;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
