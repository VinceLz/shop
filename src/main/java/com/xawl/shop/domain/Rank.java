package com.xawl.shop.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 会员等级
 * 
 * @author kernel
 * 
 */
public class Rank {
	private Integer rid;
	@NotEmpty
	private String rname;
	private Integer minscore; // 这个等级是和成交额有关系的
	private Integer maxscore;
	private String rcode;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Integer getMinscore() {
		return minscore;
	}

	public void setMinscore(Integer minscore) {
		this.minscore = minscore;
	}

	public Integer getMaxscore() {
		return maxscore;
	}

	public void setMaxscore(Integer maxscore) {
		this.maxscore = maxscore;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	@Override
	public String toString() {
		return "Rank [rid=" + rid + ", rname=" + rname + ", minscore="
				+ minscore + ", maxscore=" + maxscore + ", rcode=" + rcode
				+ "]";
	}

}
