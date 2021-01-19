package com.javen.service;

import java.util.List;

import com.javen.model.User;




public interface IUserService {
	
	int SelectCount();
	
	List<User> selectAll(int pageInteger,int limitInteger);
	
	int insert(User user);
	
	void updateByPrimaryKey(User user);
	
	void deleteByPrimaryKey(int id);

}
