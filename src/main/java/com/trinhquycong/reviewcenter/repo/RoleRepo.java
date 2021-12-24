package com.trinhquycong.reviewcenter.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trinhquycong.reviewcenter.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
