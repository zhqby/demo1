package com.javen.service;

import java.util.List;

import com.javen.model.UserManger;

public interface IUserMangerService {
int SelectCount();
	
	List<UserManger> selectAll(int pageinteger,int limitinteger);

}
