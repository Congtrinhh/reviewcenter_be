package com.trinhquycong.reviewcenter.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trinhquycong.reviewcenter.entity.Resource;
import com.trinhquycong.reviewcenter.entity.Role;
import com.trinhquycong.reviewcenter.repo.ResourceRepo;
import com.trinhquycong.reviewcenter.repo.RoleRepo;

@SpringBootTest
public class ResourceRepositoryTest {
	@Autowired
	private ResourceRepo resourceRepository;
	
	@Autowired
	private RoleRepo roleRepository;
	
	@Test
	public void insertAndPrint() {
		
//		Role roleAdmin = roleRepository.findByName("MANAGER");
//		
//		if (roleAdmin==null) return;
//		
//		
//		
//		Resource resource1 = resourceRepository.findByName("category");
//		
//		
//		
//		Resource resource2 = resourceRepository.findByName("resource");
//		
//
//		
//		Resource resource3 = resourceRepository.findByName("center");
//
//		
//		Resource resource4 = resourceRepository.findByName("rating");
//		
//		
//		
//		Resource resource5 = resourceRepository.findByName("user");
//		
//		
//		roleAdmin.setResources(Set.of(resource1, resource2, resource3, resource4, resource5));
//
//		
//		roleRepository.save(roleAdmin);
	}

}
