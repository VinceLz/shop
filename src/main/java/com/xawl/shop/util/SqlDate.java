package com.xawl.shop.util;

import java.sql.Timestamp;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.xawl.shop.domain.User;

public class SqlDate extends Timestamp{

	public SqlDate(long time) {
		super(time);
	}
	@Override
	public String toString() {
		return toLocaleString();
	}
	@Test
	public void s(){
		String json="";
		JSONObject  j=new JSONObject();
		JSONObject.toBean(j, User.class);
	}
}
