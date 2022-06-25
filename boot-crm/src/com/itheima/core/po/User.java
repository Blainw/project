package com.itheima.core.po;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID=1L;
	private Integer user_id;
	private String user_code;
	private String user_name;
	private String user_password;
	private Integer user_state;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer userId) {
		user_id = userId;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String userCode) {
		user_code = userCode;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String userPassword) {
		user_password = userPassword;
	}
	public Integer getUser_state() {
		return user_state;
	}
	public void setUser_state(Integer userState) {
		user_state = userState;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
