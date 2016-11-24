package com.xawl.shop.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.xawl.shop.util.ArrayUtil;

public class Goods {
	private Integer gid;
	@NotEmpty
	private String gname;
	@NotNull
	private double gprice;
	@NotEmpty
	private String cid;
	@NotNull
	private Integer bid;
	private Float gscore;
	@NotEmpty
	private String gcontent;
	private String gimage;
	private String glocation;
	@NotEmpty
	private String gphone;
	@NotEmpty
	private String bname;
	private Integer status;
	@NotEmpty
	private String gkey;
	private String gdate;
	@NotEmpty
	private String cname;
	private Integer sale;

	public Integer getSale() {
		return sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
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

	public void setGname(String gname) {
		this.gname = gname;
	}

	

	public double getGprice() {
		return gprice;
	}

	public void setGprice(double gprice) {
		this.gprice = gprice;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Float getGscore() {
		return gscore;
	}

	public void setGscore(Float gscore) {
		this.gscore = gscore;
	}

	public String getGcontent() {
		return gcontent;
	}

	public void setGcontent(String gcontent) {
		this.gcontent = gcontent;
	}

	public String[] getGimage() {
		return ArrayUtil.stringImage2Array(gimage);
	}

	public void setGimage(String gimage) {
		this.gimage = gimage;
	}

	public String getGlocation() {
		return glocation;
	}

	public void setGlocation(String glocation) {
		this.glocation = glocation;
	}

	public String getGphone() {
		return gphone;
	}

	public void setGphone(String gphone) {
		this.gphone = gphone;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getGkey() {
		return gkey;
	}

	public void setGkey(String gkey) {
		this.gkey = gkey;
	}

	public String getGdate() {
		return gdate;
	}

	public void setGdate(String gdate) {
		this.gdate = gdate;

	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
