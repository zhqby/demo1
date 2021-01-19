package com.javen.dao;



import java.util.List;

import com.javen.model.Login;


public interface LoginDao {
	
	Login selectByManName(String userName);
	
	int deleteByPrimaryKey(int id);
	
	int insert(Login login);
	
	int updateByPrimaryKey(Login login);
	
	List<Login> selectAll();
	
}
