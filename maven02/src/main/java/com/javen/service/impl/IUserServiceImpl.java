package com.javen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.javen.dao.UserDao;
import com.javen.model.User;
import com.javen.service.IUserService;

@Service
public class IUserServiceImpl implements IUserService{
	
	@Resource
	private UserDao userDao;

	public int SelectCount() {
		// TODO Auto-generated method stub
		return this.userDao.SelectCount();
	}

	public List<User> selectAll(int pageInteger, int limitInteger) {
		// TODO Auto-generated method stub
		int pageIndex = (pageInteger-1) * limitInteger;
		int pageSize = limitInteger;
		return this.userDao.selectAll(pageIndex, pageSize);
	}

	public int insert(User user) {
		// TODO Auto-generated method stub
		return this.userDao.insert(user);
	}

	public void updateByPrimaryKey(User user) {
		// TODO Auto-generated method stub
		this.userDao.updateByPrimaryKey(user);
	}

	public void deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		this.userDao.deleteByPrimaryKey(id);
	}

	public User selectByIdCard(int idCard) {
		// TODO Auto-generated method stub
		return this.userDao.selectByIdCard(idCard);
	}
	
	

}
