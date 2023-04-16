package com.bit.bimanda.vo;

public class UserVo {
	private Long userNo;
	private String userId;
	private String userName;
	private String userPassword;
	private String userSex;
	private String userStatus;
	private Long locationNo;
	private String userPhone;
	private Long teamNo;
	
	private LocationVo locationVo;
	
	public UserVo() {
		super();
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Long getLocationNo() {
		return locationNo;
	}
	public void setLocationNo(Long locationNo) {
		this.locationNo = locationNo;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Long getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(Long teamNo) {
		this.teamNo = teamNo;
	}
	public LocationVo getLocationVo() {
		return locationVo;
	}
	public void setLocationVo(LocationVo locationVo) {
		this.locationVo = locationVo;
	}
	@Override
	public String toString() {
		return "UserVo [userNo=" + userNo + ", userId=" + userId + ", userName=" + userName + ", userPassword="
				+ userPassword + ", userSex=" + userSex + ", userStatus=" + userStatus + ", locationNo=" + locationNo
				+ ", userPhone=" + userPhone + ", teamNo=" + teamNo + "]";
	}
}
