package com.bit.bimanda.vo;

public class MentorVo {
	private Long userNo;
	private String education;
	private String career;
	private String confirmed;
	private Long score;
	
	private UserVo userVo;
	
	// 기본 생성자
	public MentorVo() {
		super();
	}
	// Getters & Setters
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "MentorVo [userNo=" + userNo + ", education=" + education + ", career=" + career + ", confirmed="
				+ confirmed + ", score=" + score + ", userVo=" + userVo + "]";
	}
}
