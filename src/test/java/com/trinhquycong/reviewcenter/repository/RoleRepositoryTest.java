package com.trinhquycong.reviewcenter.repository;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trinhquycong.reviewcenter.repo.ResourceRepo;
import com.trinhquycong.reviewcenter.repo.RoleRepo;

@SpringBootTest
public class RoleRepositoryTest {

	@Autowired
	private RoleRepo roleRepository;
	@Autowired
	private ResourceRepo resourceRepository;

	@Test
	public void insertOne() {
//		Role role = Role.builder().name("guest").active((byte)1).createdAt(new Date()).build();
//		
//		roleRepository.save(role);
	}

	@Test
	@Transactional
	public void insertWithResource() {
		

	}
}