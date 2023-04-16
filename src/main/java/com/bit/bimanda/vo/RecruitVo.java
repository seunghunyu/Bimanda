package com.bit.bimanda.vo;

public class RecruitVo {
	private Long no;
	private String title;
	private Long userNo;
	private String content;
	private String regDate;
	private Long hit_cnt;
	private UserVo userVo;
	// 기본 생성자
	public RecruitVo() {
		super();
	}
	// Getters & Setters
	
	
	public Long getNo() {
		return no;
	}
	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


	public Long getHit_cnt() {
		return hit_cnt;
	}


	public void setHit_cnt(Long hit_cnt) {
		this.hit_cnt = hit_cnt;
	}


	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	@Override
	public String toString() {
		return "RecruitVo [no=" + no + ", title=" + title + ", userNo=" + userNo + ", content=" + content + ", userVo="
				+ userVo + "]";
	}
}
