package com.javen.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.util.RootNameLookup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javan.util.IsNull;
import com.javan.util.Obj2;
import com.javan.util.ObjtoLayJson;
import com.javen.model.InsertRoom;
import com.javen.model.Room;
import com.javen.model.User;
import com.javen.service.IRoomService;
import com.javen.service.IUserService;
import com.mysql.fabric.xmlrpc.base.Data;

@Controller
@RequestMapping("/room")
public class RoomController {

	@Resource
	private IRoomService service;
	@Resource
	private IUserService service1;

	// 插入数据 没用
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(HttpServletRequest request, Model model) throws Exception {

		String roomId = request.getParameter("roomId");
		String flag = request.getParameter("flag");
		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phoneNumber");
		String idCard = request.getParameter("idCard");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		// String timeLength = request.getParameter("timeLength");
		// String surplusTime = request.getParameter("surplusTime");

		Integer introomId = Integer.valueOf(roomId);
		Integer intflag = Integer.valueOf(flag);
		Integer intphoneNumber = Integer.valueOf(phoneNumber);
		Integer intidCard = Integer.valueOf(idCard);
		// Integer inttimeLength = Integer.valueOf(timeLength);
		// Integer intsurplusTime = Integer.valueOf(surplusTime);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = df.parse(startTime);
		Date d2 = df.parse(endTime);
		long diff = d2.getTime() - d1.getTime();// 这样得到的差值是微秒级别
		long days = diff / (1000 * 60 * 60 * 24);

		long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
		// System.out.println(""+days+"天"+hours+"小时"+minutes+"分");

		Long timelongth = days * 24 + hours;
		int inttimelongth = new Long(timelongth).intValue();

		Date now = new Date();// new Date()为获取当前系统时间
//        System.out.println(df.format(now));
		Date d3 = df.parse(df.format(now));
		long diff1 = d2.getTime() - d3.getTime();// 这样得到的差值是微秒级别
		long days1 = diff1 / (1000 * 60 * 60 * 24);

		long hours1 = (diff1 - days1 * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes1 = (diff1 - days1 * (1000 * 60 * 60 * 24) - hours1 * (1000 * 60 * 60)) / (1000 * 60);
		// System.out.println(""+days1+"天"+hours1+"小时"+minutes1+"分");

		Long surplusTime = days1 * 24 + hours1;
		int intsurplusTime = new Long(surplusTime).intValue();

		Room room = new Room();
		room.setRoomId(introomId);

		if (intflag == 0) {
			room.setFlag("未入住");
		} else if (intflag == 1) {
			room.setFlag("已预定");
		} else if (intflag == 2) {
			room.setFlag("已入住");
		}

		// room.setName(name);
		// room.setPhoneNumber(intphoneNumber);
		// room.setIdCard(intidCard);
		// room.setStartTime(startTime);
		// room.setEndTime(endTime);
		// Integer a=endTime-startTime;
		// room.setTimeLength(inttimelongth);
		// room.setSurplusTime(intsurplusTime);

		service.insert(room);
		String data = "{\"data\":\"成功\"}";
		return data;
	}

	// 查询全部房间信息
	@ResponseBody
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	public String selectAll(HttpServletRequest request, Model model) throws Exception {

		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");

		Integer pageInteger = Integer.valueOf(pageString);
		Integer limitInteger = Integer.valueOf(limitString);

		List<Room> list = service.selectAll(pageInteger, limitInteger);
		
		for (Room room : list) {
			if(room.getSurplusTime()<=0) {
				Integer roomId=room.getRoomId();
				service.deleteByRoomId(roomId);
				InsertRoom insertRoom =new InsertRoom();
				insertRoom.setRoomId(roomId);
				insertRoom.setFlag("未入住");
				service.updateByRomId(insertRoom);
			}
		}

		String[] colums = { "id", "roomId", "flag", "userName", "phoneNumber", "idCard", "startTime", "endTime",
				"timeLength", "surplusTime" };
		// String[] colums = { "timeLength","endTime","surplusTime"};
		String data = Obj2.ListtoJson(list, colums);
		System.out.println(data);
		return data;

	}

	// 查询全部房间个数
	@ResponseBody
	@RequestMapping(value = "/selectCount", method = RequestMethod.GET)
	public String selectCount(HttpServletRequest request, Model model) throws Exception {

		int count = service.SelectCount();
		String data = String.valueOf(count);
		// System.out.println("count:" + count);
		String json = "{" + "\"count\":" + data + "}";
		return json;

	}

	// 查询空房间个数
	@ResponseBody
	@RequestMapping(value = "/selectCountFree", method = RequestMethod.GET)
	public String selectCountFree(HttpServletRequest request, Model model) throws Exception {

		int count = service.SelectCountFree();
		String data = String.valueOf(count);
		System.out.println("count:" + count);
		String json = "{" + "\"count\":" + data + "}";
		return json;

	}

	// 查询所有空房间
	@ResponseBody
	@RequestMapping(value = "/selectFree", method = RequestMethod.GET)
	public String selectFree(HttpServletRequest request, Model model) throws Exception {

		String pageString = request.getParameter("page");
		String limitString = request.getParameter("limit");

		Integer pageInteger = Integer.valueOf(pageString);
		Integer limitInteger = Integer.valueOf(limitString);

		List<InsertRoom> list = service.selectFree(pageInteger, limitInteger);
		for (InsertRoom insertRoom : list) {
			System.out.println(insertRoom);
		}
		String[] colums = { "roomId", "flag" };
		// String[] colums = { "timeLength","endTime","surplusTime"};
		String data = ObjtoLayJson.ListtoJson(list, colums);
		System.out.println(data);
		return data;

	}

	// 添加房间
	@ResponseBody
	@RequestMapping(value = "/insertRoom", method = RequestMethod.GET)
	public String insertRoom(HttpServletRequest request, Model model) throws Exception {

		String roomId = request.getParameter("roomId");
		String flag = request.getParameter("flag");
		Integer introomId = Integer.valueOf(roomId);
		Integer intflag = Integer.valueOf(flag);

		InsertRoom room = new InsertRoom();
		if (intflag == 0) {
			room.setFlag("未入住");
		} else if (intflag == 1) {
			room.setFlag("已预定");
		} else if (intflag == 2) {
			room.setFlag("已入住");
		}
		room.setRoomId(introomId);
		service.insertRoom(room);
		String data = "{\"data\":\"成功\"}";
		return data;

	}

	// 删除房间用户退订或者预定超时的房间
	@ResponseBody
	@RequestMapping(value = "/deleteByRoomId", method = RequestMethod.GET)
	public String deleteByRoomId(HttpServletRequest request, Model model) throws Exception {

		String idString = request.getParameter("roomId");
		Integer idInteger = Integer.valueOf(idString);
		service.deleteByRoomId(idInteger);
		InsertRoom insertRoom = new InsertRoom();
		insertRoom.setRoomId(idInteger);
		insertRoom.setFlag("未入住");
		service.updateByRomId(insertRoom);
		String data = "{\"data\":\"返回成功\"}";
		return data;

	}

	// 开房
	@ResponseBody
	@RequestMapping(value = "/updateByRomId", method = RequestMethod.GET)
	public String updateByRomId(HttpServletRequest request, Model model) throws Exception {

		String roomId = request.getParameter("roomId");
		String flag = request.getParameter("flag");

		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phoneNumber");

		String idCard = request.getParameter("idCard");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
//		String timeLength = request.getParameter("timeLength");
//		String surplusTime = request.getParameter("surplusTime");
		
		String[] columns= {roomId,flag,name,phoneNumber,idCard,startTime,endTime};
		Boolean boolean1=IsNull.isNull(columns);
		if(boolean1==false) {
			String data = "{\"data\":\"失败\"}";
			return data;
		}

		
		
		Integer introomId = Integer.valueOf(roomId);
		Integer intflag = Integer.valueOf(flag);
		Integer intphoneNumber = Integer.valueOf(phoneNumber);
		Integer intidCard = Integer.valueOf(idCard);
//		Integer inttimeLength = Integer.valueOf(timeLength);
//		Integer intsurplusTime = Integer.valueOf(surplusTime);
		
		User user=service1.selectByIdCard(intidCard);
		if(user.getUserName().equals(name)==false||user.getPhoneNumber()!=intphoneNumber) {
			String data = "{\"data\":\"失败\"}";
			return data;
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = df.parse(startTime);
		Date d2 = df.parse(endTime);
		long diff = d2.getTime() - d1.getTime();// 这样得到的差值是微秒级别
		long days = diff / (1000 * 60 * 60 * 24);

		long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
//	    long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
//	    System.out.println(""+days+"天"+hours+"小时"+minutes+"分");

		Long timelongth = days * 24 + hours;
		int inttimelongth = new Long(timelongth).intValue();

		Date now = new Date();// new Date()为获取当前系统时间
//        System.out.println(df.format(now));
		Date d3 = df.parse(df.format(now));
		long diff1 = d2.getTime() - d3.getTime();// 这样得到的差值是微秒级别
		long days1 = diff1 / (1000 * 60 * 60 * 24);

		long hours1 = (diff1 - days1 * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes1 = (diff1 - days1 * (1000 * 60 * 60 * 24) - hours1 * (1000 * 60 * 60)) / (1000 * 60);
		// System.out.println(""+days1+"天"+hours1+"小时"+minutes1+"分");

		Long surplusTime = days1 * 24 + hours1;
		int intsurplusTime = new Long(surplusTime).intValue();

		InsertRoom insertRoom = new InsertRoom();
		if (intflag == 0) {
			insertRoom.setFlag("未入住");
		} else if (intflag == 1) {
			insertRoom.setFlag("已预定");
		} else if (intflag == 2) {
			insertRoom.setFlag("已入住");
		}
		insertRoom.setRoomId(introomId);
		service.updateByRomId(insertRoom);

		Room room = new Room();
		room.setRoomId(introomId);
		room.setIdCard(intidCard);
		room.setStartTime(startTime);
		room.setEndTime(endTime);
		room.setTimeLength(inttimelongth);
		room.setSurplusTime(intsurplusTime);
		service.insert(room);

		String data = "{\"data\":\"成功\"}";
		return data;
	}

}
