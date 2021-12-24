package com.trinhquycong.reviewcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trinhquycong.reviewcenter.repo.ResourceRepo;
import com.trinhquycong.reviewcenter.util.Constants;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceRepo resourceRepo;
	
	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		try {
			resourceRepo.deleteById(id);
		} catch (Exception e) {
			return e.getMessage();
		}
		return Constants.DELETE_SUCCESSFULLY_MSG;
	}

}
