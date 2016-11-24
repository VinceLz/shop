package com.xawl.shop.domain;

import org.hibernate.validator.constraints.NotEmpty;

import com.xawl.shop.util.ArrayUtil;

public class Business {
	private String bid;
	@NotEmpty
	private String bname;
	private String bscore;
	private String bimage;
	@NotEmpty
	private String baddress;
	private String companyname;
	private Integer status;
	private String bdate;
	private Integer rid;
	private String rname;
	@NotEmpty
	private String bphone;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBscore() {
		return bscore;
	}

	public void setBscore(String bscore) {
		this.bscore = bscore;
	}

	public String[] getBimage() {
		return ArrayUtil.stringImage2Array(bimage);
	}

	public void setBimage(String bimage) {
		this.bimage = bimage;
	}

	public String getBaddress() {
		return baddress;
	}

	public void setBaddress(String baddress) {
		this.baddress = baddress;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getBphone() {
		return bphone;
	}

	public void setBphone(String bphone) {
		this.bphone = bphone;
	}

	@Override
	public String toString() {
		return "Business [bid=" + bid + ", bname=" + bname + ", bscore="
				+ bscore + ", bimage=" + bimage + ", baddress=" + baddress
				+ ", companyname=" + companyname + ", status=" + status
				+ ", bdate=" + bdate + ", rid=" + rid + ", rname=" + rname
				+ ", bkey=" + ", bphone=" + bphone + "]";
	}

}
