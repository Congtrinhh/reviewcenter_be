package com.trinhquycong.reviewcenter.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.trinhquycong.reviewcenter.entity.User;

public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	User findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	Page<User> findByRolesNameEqualsAndDisplayNameContains(String roleName, String displayName, Pageable pageable);
	
	Page<User> findByRolesNameEqualsAndDisplayNameContainsAndActiveEquals(String roleName, String displayName, Boolean active, Pageable pageable);
}
