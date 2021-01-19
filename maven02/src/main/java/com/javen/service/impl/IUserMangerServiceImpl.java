package com.javen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.javen.dao.UserMangerDao;
import com.javen.model.UserManger;
import com.javen.service.IUserMangerService;

@Service
public class IUserMangerServiceImpl implements IUserMangerService{
	
	@Resource 
	private UserMangerDao userMangerDao;

	public int SelectCount() {
		// TODO Auto-generated method stub
		return this.userMangerDao.SelectCount();
	}

	public List<UserManger> selectAll(int pageinteger, int limitinteger) {
		// TODO Auto-generated method stub
		int pageIndex = (pageinteger-1) * limitinteger;
		int pageSize = limitinteger;
		return this.userMangerDao.selectAll(pageIndex, pageSize);
	}

	

}
