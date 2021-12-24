package com.trinhquycong.reviewcenter.repo;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.trinhquycong.reviewcenter.entity.Center;

public interface CenterRepo extends JpaRepository<Center, Long>, JpaSpecificationExecutor<Center> {

	Page<Center> findByNameContainsAndAddressContainsAndCategoryIdEqualsAndActiveEqualsAndSizeIn(
			String name, String address, Long categoryId, Boolean active, Collection<?> sizes, Pageable pageable);
	
	
}
