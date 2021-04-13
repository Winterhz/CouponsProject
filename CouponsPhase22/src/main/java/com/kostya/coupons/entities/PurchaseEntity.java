package com.kostya.coupons.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kostya.coupons.dto.purchaseDto.PurchaseDto;

@Entity
@Table(name = "purchases")
public class PurchaseEntity {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "amount", nullable = false)
	private int amount;
	
	@Column(name = "timestamp")
	private Date timeStamp;
	
	@ManyToOne
	private UserEntity user;
	
	@ManyToOne
	private CouponEntity coupon;

	public PurchaseEntity(long id, int amount, Date timeStamp, UserEntity user, CouponEntity coupon) {
		super();
		this.id = id;
		this.amount = amount;
		this.timeStamp = timeStamp;
		this.user = user;
		this.coupon = coupon;
	}

	public PurchaseEntity(PurchaseDto purchaseDto) {
		super();
		this.id = purchaseDto.getId();
		this.amount = purchaseDto.getAmount();
		this.timeStamp = purchaseDto.getTimeStamp();
	}

	public PurchaseEntity() {
		super();
	}

	@Override
	public String toString() {
		return "PurchaseEntity [id=" + id + ", amount=" + amount + ", timeStamp=" + timeStamp + ", user=" + user
				+ ", coupon=" + coupon + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CouponEntity getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponEntity coupon) {
		this.coupon = coupon;
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
	
}
