package com.xawl.shop.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 分类
 * 
 * @author kernel
 * 
 */
public class Category {
	@NotEmpty
	private String cid;
	@NotEmpty
	private String cname;
	@NotEmpty
	private String pid;
	private String desc;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
