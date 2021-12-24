package com.trinhquycong.reviewcenter.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.trinhquycong.reviewcenter.entity.Category;
import com.trinhquycong.reviewcenter.repo.CategoryRepo;
import com.trinhquycong.reviewcenter.repo.CenterRepo;

@SpringBootTest
public class CategoryRepositoryTest {
	@Autowired
	private CategoryRepo categoryRepository;
	
	@Autowired
	private CenterRepo centerRepository;

	@Test
	public void insertAndPrint() {


	}

	@Test
	public void updateAndPrint() {
		int r = categoryRepository.updateName("", "new name after seeing problem");
		System.out.println(r);
	}

	@Test
	public void printAll() {
		List<Category> categories = categoryRepository.findByNameContaining("");
		categories.forEach(c -> {
			System.out.println(c.toString());
		});
	}
}
