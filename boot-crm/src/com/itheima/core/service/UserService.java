package com.itheima.core.service;

import com.itheima.core.po.Customer;
import com.itheima.core.po.User;

public interface UserService {

	public User findUser(String usercode,String password);
	
}
