package com.trinhquycong.reviewcenter.dto;

public class RatingSearchDto {
	
	private Long centerId;
	
	private Long userId;
	
	private Integer rate;
	
	private String comment;
	
	private Boolean active;

	public RatingSearchDto() {
		super();
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
}
