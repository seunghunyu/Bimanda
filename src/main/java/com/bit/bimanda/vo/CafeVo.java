package com.bit.bimanda.vo;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class CafeVo {
	private XSSFCell links;
	private XSSFCell contents;
	@Override
	public String toString() {
		return "CafeVo [links=" + links + ", contents=" + contents + "]";
	}
	public XSSFCell getLinks() {
		return links;
	}
	public void setLinks(XSSFCell links) {
		this.links = links;
	}
	public XSSFCell getContents() {
		return contents;
	}
	public void setContents(XSSFCell contents) {
		this.contents = contents;
	}
	
	
	
}
