package com.trinhquycong.reviewcenter.dto;

public class CenterSearchDto {
	
	private Long categoryId;
	
	private String name;
	
	private String description;
	
	private String address;
	
	private Integer centerSize;
	
	private Integer sizeMin;
	
	private Integer sizeMax;
	
	private Boolean active;

	public CenterSearchDto() {
		super();
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCenterSize() {
		return centerSize;
	}

	public void setCenterSize(Integer centerSize) {
		this.centerSize = centerSize;
	}

	public Integer getSizeMin() {
		return sizeMin;
	}

	public void setSizeMin(Integer sizeMin) {
		this.sizeMin = sizeMin;
	}

	public Integer getSizeMax() {
		return sizeMax;
	}

	public void setSizeMax(Integer sizeMax) {
		this.sizeMax = sizeMax;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
