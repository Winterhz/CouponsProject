package com.kostya.coupons.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kostya.coupons.dto.couponDto.CouponDto;
import com.kostya.coupons.dto.couponDto.CouponDtoForUpdate;
import com.kostya.coupons.enums.CategoryType;

@Entity
@Table(name = "coupons")
public class CouponEntity {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;

	@Column(name = "name", nullable = false, updatable = false)
	private String name;

	@Column(name = "price", nullable = false)
	private long price;

	@Column(name = "description")
	private String description;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "category_type", nullable = false)
	private CategoryType categoryType;

	@Column(name = "amount", nullable = false)
	private int amount;

	@Column(name = "image")
	private String image;

	@ManyToOne
	@JsonIgnore
	private CompanyEntity company;

	@OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE)
	private List<PurchaseEntity> purchases;

	public CouponEntity(long id, String name, long price, String description, Date startDate, Date endDate,
			CategoryType categoryType, int amount, String image, CompanyEntity company) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.categoryType = categoryType;
		this.amount = amount;
		this.image = image;
		this.company = company;
	}
	
	public CouponEntity(CouponDtoForUpdate couponDto) {
		super();
		this.id = couponDto.getId();
		this.price = couponDto.getPrice();
		this.description = couponDto.getDescription();
		this.startDate = couponDto.getStartDate();
		this.endDate = couponDto.getEndDate();
		this.categoryType = couponDto.getCategoryType();
		this.amount = couponDto.getAmount();
		this.image = couponDto.getImage();
	}
	
	public CouponEntity(CouponDto couponDto) {
		super();
		this.name = couponDto.getName();
		this.price = couponDto.getPrice();
		this.description = couponDto.getDescription();
		this.startDate = couponDto.getStartDate();
		this.endDate = couponDto.getEndDate();
		this.categoryType = couponDto.getCategoryType();
		this.amount = couponDto.getAmount();
		this.image = couponDto.getImage();
	}

	public CouponEntity() {
		super();
	}

	@Override
	public String toString() {
		return "CouponEntity [id=" + id + ", name=" + name + ", Price=" + price + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", categoryType=" + categoryType + ", amount="
				+ amount + ", image=" + image + ", company=" + company + ", purchases=" + purchases + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public List<PurchaseEntity> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseEntity> purchases) {
		this.purchases = purchases;
	}

}
