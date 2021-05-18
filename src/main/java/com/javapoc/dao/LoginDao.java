package com.javapoc.dao;


public interface LoginDao {

	 public boolean validateLogin(String username, String password);
	 public String getLoginData(String username);
	
}
