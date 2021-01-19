package com.javen.model;

public class InsertRoom {
	private Integer roomId;
	private String flag;
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "InsertRoom [roomId=" + roomId + ", flag=" + flag + "]";
	}
	
	
}
