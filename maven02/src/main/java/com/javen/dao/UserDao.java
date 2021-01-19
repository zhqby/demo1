package com.javen.dao;

import java.util.List;

import com.javen.model.User;




public interface UserDao {
	
	int SelectCount();
	
	List<User> selectAll(int pageIndex,int pageSize);
	
	int insert(User user);
	
	void updateByPrimaryKey(User user);
	
	void deleteByPrimaryKey(int id);

}
