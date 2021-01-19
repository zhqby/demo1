package com.javen.dao;

import java.util.List;

import com.javen.model.InsertRoom;
import com.javen.model.Room;

public interface RoomDao {
	
	void insert(Room room);
	
	int SelectCount();
	
	List<Room> selectAll(int pageindex,int pagesize);
	
	void deleteByRoomId(int roomid);
	
	void updateByRomId(InsertRoom room);
	
	void insertRoom(InsertRoom insertRoom);

}
