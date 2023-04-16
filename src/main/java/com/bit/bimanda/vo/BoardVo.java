package com.bit.bimanda.vo;

public class BoardVo {
	private Long boardNo;
	private String boardTitle;
	private String boardCategory;
	private Long userNo;
	private String boardContent;
	private String regDate;
	private Long hit_cnt;
	
	private UserVo userVo;
	
	
	public Long getHit_cnt() {
		return hit_cnt;
	}
	public void setHit_cnt(Long hit_cnt) {
		this.hit_cnt = hit_cnt;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public BoardVo() {
		super();
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	public Long getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardCategory() {
		return boardCategory;
	}
	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	@Override
	public String toString() {
		return "BoardVo [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardCategory=" + boardCategory
				+ ", userNo=" + userNo + ", boardContent=" + boardContent + ", userVo=" + userVo + "]";
	}
}
