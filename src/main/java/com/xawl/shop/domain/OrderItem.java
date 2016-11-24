package com.xawl.shop.domain;

public class OrderItem {
	private Integer orderitem_id;
	private Integer gid;
	private String gname;
	private String gimage;
	private String oid;
	private double gprice;

	public Integer getOrderitem_id() {
		return orderitem_id;
	}

	public void setOrderitem_id(Integer orderitem_id) {
		this.orderitem_id = orderitem_id;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public void setGname(String gname) {

		this.gname = gname;
	}

	// public String[] getGimage() {
	// return ArrayUtil.stringImage2Array(gimage);
	// }
	public String getGimage() {
		return gimage;
	}

	public void setGimage(String gimage) {
		this.gimage = gimage;
	}

	public double getGprice() {
		return gprice;
	}

	public void setGprice(double gprice) {
		this.gprice = gprice;
	}



}
