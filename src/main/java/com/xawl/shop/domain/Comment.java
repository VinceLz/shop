package com.xawl.shop.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class Comment {
	private Integer comment_id;
	@NotEmpty
	private String gname;
	@NotEmpty
	private String content;
	@NotNull
	private Integer gid;
	private String date;
	@NotEmpty
	private String uname;
	
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	
	
}
