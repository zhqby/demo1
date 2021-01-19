package com.javen.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javan.util.ObjtoLayJson;
import com.javen.model.User;
import com.javen.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService service;

	// 分页查询
	@ResponseBody
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public String selectall(HttpServletRequest request, Model model) throws Exception {

		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");

		Integer pageInteger = Integer.valueOf(pageString);
		Integer limitInteger = Integer.valueOf(limitString);
		// System.out.println(pageString + limitString);

		List<User> list = service.selectAll(pageInteger, limitInteger);

		String[] colums = { "id", "userName", "password" ,"phoneNumber","idCard"};
		String data = ObjtoLayJson.ListtoJson(list, colums);
		return data;
	}

	// 查询表中有多少条数据
	@ResponseBody
	@RequestMapping(value = "/selectCount", method = RequestMethod.GET)
	public String SelectCount(HttpServletRequest request, Model model) throws Exception {

		int count = service.SelectCount();
		String data = String.valueOf(count);
		System.out.println("count:" + count);
		String json = "{" + "\"count\":" + data + "}";
		return json;
	}

	// 插入数据
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(HttpServletRequest request, Model model) throws Exception {

		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		String idCard = request.getParameter("idCard");
		Integer intphoneNumber = Integer.valueOf(phoneNumber);
		Integer intidCard = Integer.valueOf(idCard);

		User user = new User();
		user.setUserName(username);
		user.setPassword(password);
		user.setIdCard(intidCard);
		user.setPhoneNumber(intphoneNumber);
		

		service.insert(user);
		String data = "{\"data\":\"成功\"}";
		return data;
	}

	// 修改数据
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String update(HttpServletRequest request) {
		// 插入数据库
		String idString = request.getParameter("id");
		String usernameString = request.getParameter("userName");
		String passwordString = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		String idCard = request.getParameter("idCard");
		Integer intphoneNumber = Integer.valueOf(phoneNumber);
		Integer intidCard = Integer.valueOf(idCard);

		Integer idInteger = Integer.valueOf(idString);

		User user = new User();
		user.setId(idInteger);
		user.setUserName(usernameString);
		user.setPassword(passwordString);
		user.setIdCard(intidCard);
		user.setPhoneNumber(intphoneNumber);

		service.updateByPrimaryKey(user);

		// 给前台返回的东西
		String data = "{\"data\":\"返回成功\"}";
		return data;
	}

	// 删除
	@ResponseBody
	@RequestMapping(value = "/deleteByPrimaryKey", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String deleteByPrimaryKey(HttpServletRequest request) {
		String idString = request.getParameter("id");
		Integer idInteger = Integer.valueOf(idString);
		service.deleteByPrimaryKey(idInteger);
		String data = "{\"data\":\"返回成功\"}";
		return data;
	}

}
