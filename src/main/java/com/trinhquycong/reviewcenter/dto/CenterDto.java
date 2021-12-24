package com.trinhquycong.reviewcenter.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

public class CenterDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long categoryId;
	
	@NotBlank
	private String name;
	
	private String description;
	
	@NotBlank
	private String address;
	
	private Integer centerSize;
	
	private Integer sizeMin;
	
	private Integer sizeMax;
	
	private byte[] avatar;
	
	private Boolean active;
	
	private Date createdAt;
	
	private Date updatedAt;

	public CenterDto() {
		super();
	}

	public CenterDto(Long categoryId, @NotBlank String name, String description, @NotBlank String address,
			Integer centerSize, Integer sizeMin, Integer sizeMax, byte[] avatar, Boolean active, Date createdAt,
			Date updatedAt) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.address = address;
		this.centerSize = centerSize;
		this.sizeMin = sizeMin;
		this.sizeMax = sizeMax;
		this.avatar = avatar;
		this.active = active;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public Integer getCenterSize() {
		return centerSize;
	}

	public void setCenterSize(Integer centerSize) {
		this.centerSize = centerSize;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
