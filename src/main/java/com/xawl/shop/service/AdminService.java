package com.xawl.shop.service;

import com.xawl.shop.domain.Admin;

public interface AdminService {
	Admin getAdmin(String usernmae,String password);
}
