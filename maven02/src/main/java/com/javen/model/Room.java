package com.javen.model;

public class Room {
	private Integer id;
	private Integer roomId;
	private String flag;
	private String userName;
	private int phoneNumber;
	private int idCard;
	private String startTime;
	private String endTime;
	private int timeLength;
	private int surplusTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getIdCard() {
		return idCard;
	}
	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getTimeLength() {
		return timeLength;
	}
	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
	}
	public int getSurplusTime() {
		return surplusTime;
	}
	public void setSurplusTime(int surplusTime) {
		this.surplusTime = surplusTime;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", roomId=" + roomId + ", flag=" + flag + ", userName=" + userName + ", phoneNumber="
				+ phoneNumber + ", idCard=" + idCard + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", timeLength=" + timeLength + ", surplusTime=" + surplusTime + "]";
	}
	
	
	
	
	

}
