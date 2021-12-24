package com.trinhquycong.reviewcenter.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trinhquycong.reviewcenter.entity.Resource;

public interface ResourceRepo extends JpaRepository<Resource, Long> {
	
	Resource findByName(String name);
}
