package com.bit.bimanda.vo;

public class CategoryVo {
	private Long cateNo;
	private String cateName;
	
	@Override
	public String toString() {
		return "CategoryVo [cateNo=" + cateNo + ", cateName=" + cateName + "]";
	}
	public Long getCateNo() {
		return cateNo;
	}
	public void setCateNo(Long cateNo) {
		this.cateNo = cateNo;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
}
