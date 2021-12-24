package com.trinhquycong.reviewcenter.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.trinhquycong.reviewcenter.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

	@Transactional
	@Modifying
	@Query("update Category c set c.name=:newName where c.name=:currentName")
	int updateName(@Param("currentName") String currentName,  @Param("newName") String newName);
	
	List<Category> findByNameContaining(String name);

	Category findByName(String name);
	
	Page<Category> findByNameContains(String name, Pageable pageable);
	
	Page<Category> findByNameContainsAndActiveEquals(String name, Boolean active, Pageable pageable);
}
