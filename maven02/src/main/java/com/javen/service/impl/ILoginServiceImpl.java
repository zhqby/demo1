package com.javen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.javen.dao.LoginDao;
import com.javen.model.Login;
import com.javen.service.ILoginService;

@Service
public class ILoginServiceImpl implements ILoginService{

	@Resource
	private LoginDao loginDao;
	
	
	
	

	public int deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return this.loginDao.deleteByPrimaryKey(id);
	}

	public int insert(Login login) {
		// TODO Auto-generated method stub
		return this.loginDao.insert(login);
	}

	public int updateByPrimaryKey(Login login) {
		// TODO Auto-generated method stub
		return this.loginDao.updateByPrimaryKey(login);
	}

	public List<Login> selectAll() {
		// TODO Auto-generated method stub
		return this.loginDao.selectAll();
	}

	public Login selectByManName(String userName) {
		// TODO Auto-generated method stub
		return this.loginDao.selectByManName(userName);
	}

	public Login selectByUserName(String userName) {
		// TODO Auto-generated method stub
		return this.loginDao.selectByUserName(userName);
	}

}
