package com.bit.bimanda.vo;

import java.util.Date;

public class Team_ChatVo {
	private long chatNo;
	private long teamNo;
	private Date chatDate;
	private long userNo;
	private String userId;
	private String chatContent;
	
	@Override
	public String toString() {
		return "Team_ChatVo [chatNo=" + chatNo + ", teamNo=" + teamNo + ", chatDate=" + chatDate + ", userNo=" + userNo
				+ ", userId=" + userId + ", chatContent=" + chatContent + "]";
	}
	public long getChatNo() {
		return chatNo;
	}
	public void setChatNo(long chatNo) {
		this.chatNo = chatNo;
	}
	public long getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(long teamNo) {
		this.teamNo = teamNo;
	}
	public Date getChatDate() {
		return chatDate;
	}
	public void setChatDate(Date chatDate) {
		this.chatDate = chatDate;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	
	
}
