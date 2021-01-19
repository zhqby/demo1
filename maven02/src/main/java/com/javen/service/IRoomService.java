package com.javen.service;

import java.util.List;

import com.javen.model.InsertRoom;
import com.javen.model.Room;

public interface IRoomService {
	void insert(Room room);
	
	int SelectCount();
	
	List<Room> selectAll(int pageinteger,int limitinteger);
	
	void deleteByRoomId(int roomid);
	

	
	void insertRoom(InsertRoom insertRoom);

	void updateByRomId(InsertRoom insertRoom);

}
