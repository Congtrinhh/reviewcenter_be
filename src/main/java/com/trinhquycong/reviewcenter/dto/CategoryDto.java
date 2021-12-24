package com.trinhquycong.reviewcenter.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

public class CategoryDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Long id;
	
	@NotBlank
	protected String name;
	
	protected Boolean active;
	
	protected Date createdAt;
	
	protected Date updatedAt;
	
	public CategoryDto() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
