package com.trinhquycong.reviewcenter.dto;

import java.io.Serializable;
import java.util.Date;

public class RatingDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long centerId;
	
	private Long userId;
	
	private Integer rate;
	
	private String comment;
	
	private Boolean active;
	
	private Date createdAt;
	
	private Date updatedAt;

	public RatingDto(Long centerId, Long userId, Integer rate, String comment, Boolean active, Date createdAt,
			Date updatedAt) {
		super();
		this.centerId = centerId;
		this.userId = userId;
		this.rate = rate;
		this.comment = comment;
		this.active = active;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public RatingDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCenterId() {
		return centerId;
	}

	public void setCenterId(Long centerId) {
		this.centerId = centerId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
