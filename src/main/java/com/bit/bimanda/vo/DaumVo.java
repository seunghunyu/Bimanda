package com.bit.bimanda.vo;

public class DaumVo {
	private String title;
	private String url;
	private String contents;
	private String thumbnail;
	
	@Override
	public String toString() {
		return "DaumVo [title=" + title + ", url=" + url + ", contents=" + contents + ", thumbnail=" + thumbnail + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
	
}
