package com.trinhquycong.reviewcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trinhquycong.reviewcenter.repo.CenterRepo;
import com.trinhquycong.reviewcenter.util.Constants;

@Service
public class CenterServiceImpl implements CenterService {

	@Autowired
	private CenterRepo centerRepo;

	@Override
	public String deleteById(Long id) {
		try {
			centerRepo.deleteById(id);
		}catch (Exception e) {
			return e.getMessage();
		}
		return Constants.DELETE_SUCCESSFULLY_MSG;
	}
}
