package com.bit.bimanda.vo;

import java.util.Date;

public class CommentsVo {
	private Long commentNo;
	private Long boardNo;
	private Long userNo;
	private String commentContent;
	private String regDate;
	UserVo userVo;
	
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "CommentsVo [commentNo=" + commentNo + ", boardNo=" + boardNo + ", userNo=" + userNo
				+ ", commentContent=" + commentContent + ", userVo=" + userVo + "]";
	}
	public Long getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(Long commentNo) {
		this.commentNo = commentNo;
	}
	public Long getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	
	
}
