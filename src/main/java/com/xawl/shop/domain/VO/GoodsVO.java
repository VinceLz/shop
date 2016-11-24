package com.xawl.shop.domain.VO;

import java.util.List;

import com.xawl.shop.domain.Business;
import com.xawl.shop.domain.Comment;

public class GoodsVO {
	private String gid;
	private String gname;
	private String gprice;
	private String cid;
	private String bid;
	private String gscore;
	private String gcontent;
	private String gimage;
	private String glocation;
	private String gphone;
	private Integer status;
	private String gkey;
	private String gdate;
	private String cname;
	private String bname;

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

	private List<Comment> mycomment;

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGprice() {
		return gprice;
	}

	public void setGprice(String gprice) {
		this.gprice = gprice;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getGscore() {
		return gscore;
	}

	public void setGscore(String gscore) {
		this.gscore = gscore;
	}

	public String getGcontent() {
		return gcontent;
	}

	public void setGcontent(String gcontent) {
		this.gcontent = gcontent;
	}

	public String getGimage() {
		return gimage;
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

	public List<Comment> getMycomment() {
		return mycomment;
	}

	public void setMycomment(List<Comment> mycomment) {
		this.mycomment = mycomment;
	}

}
