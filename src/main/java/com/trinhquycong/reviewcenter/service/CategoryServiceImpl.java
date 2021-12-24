package com.trinhquycong.reviewcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trinhquycong.reviewcenter.repo.CategoryRepo;
import com.trinhquycong.reviewcenter.util.Constants;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		try {
			categoryRepo.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
		return Constants.DELETE_SUCCESSFULLY_MSG;
	}
}
