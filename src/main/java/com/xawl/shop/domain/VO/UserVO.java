package com.xawl.shop.domain.VO;

import com.xawl.shop.domain.Business;

public class UserVO {
	private Integer uid;
	private String ulogin;//登陆名
	private String uphone;
	private String uaddress;
	private String ulongitude;//经度
	private String uname;//用户名
	private Business business;
	private String uprovince;//省
	private String ucity;//市
	private Integer status;
	private String ulatitude;//纬度
	private String udate;
	private int sex;
	
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
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
	public String getUlogin() {
		return ulogin;
	}
	public void setUlogin(String ulogin) {
		this.ulogin = ulogin;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getUlongitude() {
		return ulongitude;
	}
	public void setUlongitude(String ulongitude) {
		this.ulongitude = ulongitude;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public String getUprovince() {
		return uprovince;
	}
	public void setUprovince(String uprovince) {
		this.uprovince = uprovince;
	}
	public String getUcity() {
		return ucity;
	}
	public void setUcity(String ucity) {
		this.ucity = ucity;
	}
	public String getUlatitude() {
		return ulatitude;
	}
	public void setUlatitude(String ulatitude) {
		this.ulatitude = ulatitude;
	}
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}
	
}
