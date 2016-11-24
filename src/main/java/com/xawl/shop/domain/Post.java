package com.xawl.shop.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 买家发帖
 * 
 * @author kernel
 * 
 */
public class Post {
	private Integer pid;
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	@NotEmpty
	private String uphone;
	@NotEmpty
	private String uname;
	@NotNull
	private Integer uid;
	@NotEmpty
	private String cid;
	@NotEmpty
	private String cname;
	private Integer status;
	private String pdate;
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	
	

	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
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

	@Override
	public String toString() {
		return "Post [pid=" + pid + ", title=" + title + ", content=" + content
				+ ", uphone=" + uphone + ", uname=" + uname + ", uid=" + uid
				+ ", cid=" + cid + ", cname=" + cname + ", status=" + status
				+ ", pdate=" + pdate + "]";
	}

}
