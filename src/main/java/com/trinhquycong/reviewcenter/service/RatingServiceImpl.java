package com.trinhquycong.reviewcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trinhquycong.reviewcenter.repo.RatingRepo;
import com.trinhquycong.reviewcenter.util.Constants;
@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepo ratingRepo;

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		try {
			ratingRepo.deleteById(id);
		} catch (Exception e) {
			return e.getMessage();
		}
		return Constants.DELETE_SUCCESSFULLY_MSG;
	}

}
