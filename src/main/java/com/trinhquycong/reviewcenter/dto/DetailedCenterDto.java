package com.trinhquycong.reviewcenter.dto;

public class DetailedCenterDto {
	
	private String name;
	
	private String categoryName;
	
	private Integer size;
	
	private String address;
	
	// total of reviews
	private Integer totalRatedTimes;
	
	// 0 to 100%, for calculating star (1 to 5)
	private Float averageRatePercent;

	public DetailedCenterDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getTotalRatedTimes() {
		return totalRatedTimes;
	}

	public void setTotalRatedTimes(Integer totalRatedTimes) {
		this.totalRatedTimes = totalRatedTimes;
	}

	public Float getAverageRatePercent() {
		return averageRatePercent;
	}

	public void setAverageRatePercent(Float averageRatePercent) {
		this.averageRatePercent = averageRatePercent;
	}
}
