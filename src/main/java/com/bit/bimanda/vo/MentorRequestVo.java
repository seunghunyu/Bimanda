package com.bit.bimanda.vo;

public class MentorRequestVo {
	private Long userNo;
	private Long teamNo;
	
	private TeamVo teamVo;
	private ProjectVo projectVo;
	
	public MentorRequestVo() {
		super();
	}

	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public Long getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(Long teamNo) {
		this.teamNo = teamNo;
	}
	public TeamVo getTeamVo() {
		return teamVo;
	}
	public void setTeamVo(TeamVo teamVo) {
		this.teamVo = teamVo;
	}
	public ProjectVo getProjectVo() {
		return projectVo;
	}
	public void setProjectVo(ProjectVo projectVo) {
		this.projectVo = projectVo;
	}
	@Override
	public String toString() {
		return "MentorRequestVo [userNo=" + userNo + ", teamNo=" + teamNo + ", teamVo=" + teamVo + ", projectVo=" + projectVo
				+ "]";
	}
}	
