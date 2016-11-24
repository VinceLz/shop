package com.xawl.shop.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CartItem {
	private Integer cartitem_id;
	@NotNull
	private Integer gid;
	private Integer uid;
	@NotEmpty
	private String gname;
	@NotNull
	private Float gprice; // 后期考虑数值溢出
	private String bname;
	private String date;

	public Integer getCartitem_id() {
		return cartitem_id;
	}

	public void setCartitem_id(Integer cartitem_id) {
		this.cartitem_id = cartitem_id;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public Float getGprice() {
		return gprice;
	}

	public void setGprice(Float gprice) {
		this.gprice = gprice;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
