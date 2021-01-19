package com.javen.dao;

import java.util.List;

import com.javen.model.UserManger;



public interface UserMangerDao {
	
	int SelectCount();
	
	List<UserManger> selectAll(int pageIndex,int pageSize);

}
