package com.xawl.shop.domain.VO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.xawl.shop.domain.OrderItem;

public class OrderVO {
	private String oid;
	private String ordertime;
	private double total;
	private Integer status;
	private Integer uid;
	private String uname;
	private Integer bid;
	private String bname;
	private Integer oinspect;
	private double currentprice;
	private String bphone;
	private String uphone;
	private List<OrderItem> itemlist = new ArrayList<OrderItem>();

	public String getBphone() {
		return bphone;
	}

	public String getUphone() {
		return uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	public void setBphone(String bphone) {
		this.bphone = bphone;
	}

	public double getAllTotal() {
		double result = 0;
		for (OrderItem orderitem : itemlist) {
			result += orderitem.getGprice();
		}
		return result;
	}

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

	public List<OrderItem> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<OrderItem> itemlist) {
		this.itemlist = itemlist;
	}

}
