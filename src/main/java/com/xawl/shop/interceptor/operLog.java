package com.xawl.shop.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
 * 操作日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface operLog {
	public String table()  default "";
}
