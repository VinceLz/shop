package com.xawl.shop.domain;

//uid=0 默认是发送给全部的人
//bid=0 默认是发送给全部商家的
//status 0 未读  1已读

/*
 * 消息的分类
 * 	1 商家的消息
 * 	2 用户的消息
 * 	3管理员群发的消息
 * 			type 0  普通消息  全站消息 管理员发送的
 * 			     1  会员消息  一般是 帖子被删等
 * 			     2  商家信息
 */
public class Message {
	private Integer mid;
	private String send_name;
	private String message_id;
	private Integer uid;

	private String gname;
	private String gid;
	private String pname;
	private Integer status;
	private String message;
	private String pdate;
	private Integer type;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getSend_name() {
		return send_name;
	}

	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
