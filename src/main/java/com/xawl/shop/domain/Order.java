package com.xawl.shop.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Order {
	private String oid;
	private String ordertime;
	private double total;
	private Integer status;
	@NotNull
	private Integer uid;
	@NotEmpty
	private String uname;
	@NotNull
	private Integer bid;
	@NotEmpty
	private String bname;
	private String bphone;
	private Integer oinspect;
	private double currentprice;
	private String uphone;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Integer getOinspect() {
		return oinspect;
	}

	public void setOinspect(Integer oinspect) {
		this.oinspect = oinspect;
	}

	public double getCurrentprice() {
		return currentprice;
	}

	public void setCurrentprice(double currentprice) {
		this.currentprice = currentprice;
	}

	public String getBphone() {
		return bphone;
	}

	public void setBphone(String bphone) {
		this.bphone = bphone;
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	

}
