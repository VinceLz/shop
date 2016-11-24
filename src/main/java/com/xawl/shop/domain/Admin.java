package com.xawl.shop.domain;

import org.hibernate.validator.constraints.NotEmpty;
public class Admin {
	private Integer aid;
	@NotEmpty
	private String ausername;
	@NotEmpty
	private String apassword;
	private String aname;
	
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAusername() {
		return ausername;
	}
	public void setAusername(String ausername) {
		this.ausername = ausername;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	
}
	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", ausername=" + ausername
				+ ", apassword=" + apassword + "]";
	}
	
}
