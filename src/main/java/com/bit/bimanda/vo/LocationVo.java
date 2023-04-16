package com.bit.bimanda.vo;

public class LocationVo {
	private Long locationNo;
	private String userLocation;
	
	public LocationVo() {
		super();
	}
	public Long getLocationNo() {
		return locationNo;
	}
	public void setLocationNo(Long locationNo) {
		this.locationNo = locationNo;
	}
	public String getUserLocation() {
		return userLocation;
	}
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}
	@Override
	public String toString() {
		return "LocationVo [locationNo=" + locationNo + ", userLocation=" + userLocation + "]";
	}
	
}
