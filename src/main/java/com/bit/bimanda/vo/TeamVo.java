package com.bit.bimanda.vo;

public class TeamVo {
	private Long teamNo;
	private String teamName;
	private Long userNo;
	
	// 기본 생성자
	public TeamVo() {
		super();
	}
	// Getters & Setters
	public Long getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(Long teamNo) {
		this.teamNo = teamNo;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "TeamVo [teamNo=" + teamNo + ", teamName=" + teamName + ", userNo=" + userNo + "]";
	}
}
