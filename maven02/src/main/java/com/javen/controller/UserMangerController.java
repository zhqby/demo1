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
import com.javen.model.UserManger;
import com.javen.service.IUserMangerService;

@Controller
@RequestMapping("/userManger")
public class UserMangerController {

	@Resource
	private IUserMangerService service;
	
	
	// 分页查询
		@ResponseBody
		@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
		public String selectall(HttpServletRequest request, Model model) throws Exception {

			String pageString = request.getParameter("page");
			String limitString = request.getParameter("limit");

			Integer pageInteger = Integer.valueOf(pageString);
			Integer limitInteger = Integer.valueOf(limitString);
			// System.out.println(pageString + limitString);

			List<UserManger> list = service.selectAll(pageInteger, limitInteger);

			String[] colums = { "roomId", "userName", "password" ,"phoneNumber","idCard","endTime","surplusTime"};
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

}
