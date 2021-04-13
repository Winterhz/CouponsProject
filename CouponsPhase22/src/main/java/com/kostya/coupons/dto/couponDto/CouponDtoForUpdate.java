package com.kostya.coupons.dto.couponDto;

import java.util.Date;

import com.kostya.coupons.enums.CategoryType;

public class CouponDtoForUpdate {
	private long id;
	private long price;
	private String description;
	private Date startDate;
	private Date endDate;
	private CategoryType categoryType;
	private int amount;
	private String image;
	private String companyName;
	
	public CouponDtoForUpdate(long id, long price, String description, Date startDate, Date endDate,
			CategoryType categoryType, int amount, String image, String companyName) {
		super();
		this.id = id;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.categoryType = categoryType;
		this.amount = amount;
		this.image = image;
		this.companyName = companyName;
	}
	public CouponDtoForUpdate() {
		super();
	}


	@Override
	public String toString() {
		return "CouponDtoForUpdate [id=" + id + ", price=" + price + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", categoryType=" + categoryType + ", amount="
				+ amount + ", image=" + image + ", companyName=" + companyName + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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
