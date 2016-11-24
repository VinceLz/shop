package com.xawl.shop.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author kernel
 * 
 */
public class User {
	@NotNull
	private Integer uid;
	private String ulogin;// 登陆名
	@NotEmpty
	private String uphone;
	private String uaddress;
	private String ulongitude;// 经度
	@NotEmpty
	private String uname;// 用户名
	private String upassword;
	private String bid;
	private String uprovince;// 省
	private String ucity;// 市
	private String ulatitude;// 纬度
	private String udate;
	private String ulastlogin;
	private String uemail;
	private Integer status;
	private String uimage;
	private String rname;
	private Integer rid;
	private String token;
	private int isonline;
	private int sex;

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public int getIsonline() {
		return isonline;
	}

	public void setIsonline(int isonline) {
		this.isonline = isonline;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUimage() {
		return uimage;
	}

	public void setUimage(String uimage) {
		this.uimage = uimage;
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

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
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

	public String getUlastlogin() {
		return ulastlogin;
	}

	public void setUlastlogin(String ulastlogin) {
		this.ulastlogin = ulastlogin;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", ulogin=" + ulogin + ", uphone=" + uphone
				+ ", uaddress=" + uaddress + ", ulongitude=" + ulongitude
				+ ", uname=" + uname + ", upassword=" + upassword + ", bkey="
				+ bid + ", uprovince=" + uprovince + ", ucity=" + ucity
				+ ", ulatitude=" + ulatitude + ", udate=" + udate
				+ ", ulastlogin=" + ulastlogin + ", uemail=" + uemail
				+ ", status=" + status + ", uimage=" + uimage + ", rname="
				+ rname + ", rid=" + rid + ", token=" + token + ", isonline="
				+ isonline + "]";
	}
}
