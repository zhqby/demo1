package com.javen.model;

public class UserManger {
	//private Integer id;
	private Integer roomId;
	private String userName;
	private String password;
	private int phoneNumber;
	private int idCard;
	//private String startTime;
	private String endTime;
	//private int timeLength;
	private int surplusTime;
	//public Integer getId() {
	//	return id;
	//}
	//public void setId(Integer id) {
	//	this.id = id;
	//}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getSurplusTime() {
		return surplusTime;
	}
	public void setSurplusTime(int surplusTime) {
		this.surplusTime = surplusTime;
	}
	@Override
	public String toString() {
		return "UserManger [roomId=" + roomId + ", userName=" + userName + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", idCard=" + idCard + ", endTime=" + endTime + ", surplusTime=" + surplusTime + "]";
	}
	
	
	
	
	
	

}
