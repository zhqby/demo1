package com.javen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.javen.dao.RoomDao;
import com.javen.model.InsertRoom;
import com.javen.model.Room;
import com.javen.service.IRoomService;

@Service
public class IRoomServiceImpl implements IRoomService{
	
	@Resource
	private RoomDao roomDao;

	public void insert(Room room) {
		// TODO Auto-generated method stub
		this.roomDao.insert(room);
	}

	public int SelectCount() {
		// TODO Auto-generated method stub
		return this.roomDao.SelectCount();
	}

	public List<Room> selectAll(int pageinteger, int limitinteger) {
		// TODO Auto-generated method stub
		int pageIndex = (pageinteger-1) * limitinteger;
		int pageSize = limitinteger;
		return this.roomDao.selectAll(pageIndex, pageSize);
	}

	public void deleteByRoomId(int roomid) {
		// TODO Auto-generated method stub
		this.roomDao.deleteByRoomId(roomid);
	}

	public void updateByRomId(InsertRoom room) {
		// TODO Auto-generated method stub
		this.roomDao.updateByRomId(room);
	}

	public void insertRoom(InsertRoom insertRoom) {
		// TODO Auto-generated method stub
		this.roomDao.insertRoom(insertRoom);
	}

	

	
	

}
