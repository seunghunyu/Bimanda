package com.bit.bimanda.vo;

public class RequestVo {
	private Long reqNo;
	private Long no;
	private Long userNo;
	private String content;
	
	private RecruitVo recruitVo;
	private UserVo userVo;
	
	// 기본생성자
	public RequestVo() {
		super();
	}
	// Getters & Setters
	public Long getReqNo() {
		return reqNo;
	}
	public void setReqNo(Long reqNo) {
		this.reqNo = reqNo;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public RecruitVo getRecruitVo() {
		return recruitVo;
	}
	public void setRecruitVo(RecruitVo recruitVo) {
		this.recruitVo = recruitVo;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	@Override
	public String toString() {
		return "RequestVo [reqNo=" + reqNo + ", no=" + no + ", userNo=" + userNo + ", content=" + content
				+ ", recruitVo=" + recruitVo + ", userVo=" + userVo + "]";
	}
}
