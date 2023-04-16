package com.bit.bimanda.vo;

public class ReviewBoardVo {
	private Long boardNo;
	private String boardTitle;
	private String boardCategory;
	private Long userNo;
	private String boardContent;
	private Long cateNo;
	private String cateName;
	private String regDate;
	private CategoryVo categoryVo;
	private Long hit_cnt;
	
	public Long getHit_cnt() {
		return hit_cnt;
	}
	public void setHit_cnt(Long hit_cnt) {
		this.hit_cnt = hit_cnt;
	}
	public CategoryVo getCateVo() {
		return categoryVo;
	}
	public void setCateVo(CategoryVo cateVo) {
		this.categoryVo = cateVo;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	private UserVo userVo;
	
	public ReviewBoardVo() {
		super();
	}
	// Getters & Setters
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
	public Long getCateNo() {
		return cateNo;
	}
	public void setCateNo(Long cateNo) {
		this.cateNo = cateNo;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	@Override
	public String toString() {
		return "ReviewBoardVo [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardCategory=" + boardCategory
				+ ", userNo=" + userNo + ", boardContent=" + boardContent + ", cateNo=" + cateNo + ", userVo=" + userVo
				+ "]";
	}
}
