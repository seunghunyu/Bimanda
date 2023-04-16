package com.bit.bimanda.vo;

public class KeywordVo {
	private long userNo;
	private long teamNo;
	private String keyName;
	
	@Override
	public String toString() {
		return "KeywordVo [userNo=" + userNo + ", teamNo=" + teamNo + ", keyName=" + keyName + "]";
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public long getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(long teamNo) {
		this.teamNo = teamNo;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
	
	
}
