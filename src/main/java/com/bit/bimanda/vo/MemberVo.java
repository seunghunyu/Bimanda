package com.bit.bimanda.vo;

public class MemberVo {
	private Long memNo;
	private Long teamNo;
	private Long userNo;
	
	private TeamVo teamVo;
	private UserVo userVo;
	
	// 기본 생성자
	public MemberVo() {
		super();
	}
	// Getters & Setters
	public Long getMemNo() {
		return memNo;
	}
	public void setMemNo(Long memNo) {
		this.memNo = memNo;
	}
	public Long getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(Long teamNo) {
		this.teamNo = teamNo;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public TeamVo getTeamVo() {
		return teamVo;
	}
	public void setTeamVo(TeamVo teamVo) {
		this.teamVo = teamVo;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	@Override
	public String toString() {
		return "MemberVo [memNo=" + memNo + ", teamNo=" + teamNo + ", userNo=" + userNo + ", teamVo=" + teamVo
				+ ", userVo=" + userVo + "]";
	}
}
