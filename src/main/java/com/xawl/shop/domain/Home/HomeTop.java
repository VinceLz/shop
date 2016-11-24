package com.xawl.shop.domain.Home;

/**
 * 首页类型
 * 
 * @author kernel 0 是轮播图 1 是top
 */
public class HomeTop {
	private Integer type;// 类型
	private String image;
	private Integer cid; 
	private String title;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "HomeTop [type=" + type + ", image=" + image + ", cid=" + cid
				+ ", title=" + title + "]";
	}

}
