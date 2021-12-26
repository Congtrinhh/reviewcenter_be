package com.trinhquycong.reviewcenter.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailedRateDto {
	
	private List<Map<String, Integer>> rateInfo;
	/**
	 * {
	 * 	"rateNumber":1,
	 * 	"totalRatedTimes: 98,
	 * 	"percent": 86
	 * }
	 */
	
	private Long totalRateTimes;

	public List<Map<String, Integer>> getRateInfo() {
		return rateInfo;
	}

	public void setRateInfo(List<Map<String, Integer>> rateInfo) {
		this.rateInfo = rateInfo;
	}

	public Long getTotalRateTimes() {
		return totalRateTimes;
	}

	public void setTotalRateTimes(Long totalRateTimes) {
		this.totalRateTimes = totalRateTimes;
	}

	public DetailedRateDto() {
		super();
	}
}
