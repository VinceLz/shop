package com.xawl.shop.domain.VO;

import java.util.List;

public class CategoryVO {
	private String cid;
	private String cname;
	private String pid;
	private List<CategoryVO> children;
	private String desc;


	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<CategoryVO> getChildren() {
		return children;
	}

	public void setChildren(List<CategoryVO> children) {
		this.children = children;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "CategoryVO [cid=" + cid + ", cname=" + cname + ", pid=" + pid
				+ ", children=" + children + ", desc=" + desc + "]";
	}

}
