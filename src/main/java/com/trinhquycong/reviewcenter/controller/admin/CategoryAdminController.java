package com.trinhquycong.reviewcenter.controller.admin;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trinhquycong.reviewcenter.dto.CategoryDto;
import com.trinhquycong.reviewcenter.entity.Category;
import com.trinhquycong.reviewcenter.repo.CategoryRepo;
import com.trinhquycong.reviewcenter.service.CategoryService;

@RestController
@RequestMapping("admin")
public class CategoryAdminController {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private CategoryService categoryService;

	@PostMapping("categories")
	@ResponseBody
	public CategoryDto createOne(@RequestBody CategoryDto categoryDto) {
		Category category = mapper.map(categoryDto, Category.class);

		category.setCreatedAt(Calendar.getInstance().getTime());
		category = categoryRepo.save(category);

		return mapper.map(category, CategoryDto.class);
	}

	@GetMapping("categories/{id}")
	@ResponseBody
	public CategoryDto getOne(@PathVariable Long id) {
		Category category = categoryRepo.getById(id);

		return mapper.map(category, CategoryDto.class);
	}

	@PutMapping("categories")
	@ResponseBody
	public CategoryDto updateOne(@RequestBody CategoryDto categoryDto) {
		Category category = mapper.map(categoryDto, Category.class);
		category.setUpdatedAt(Calendar.getInstance().getTime());

		category = categoryRepo.save(category);

		return mapper.map(category, CategoryDto.class);
	}

	@DeleteMapping("categories/{id}")
	@ResponseBody
	public String deleteOne(@PathVariable(value = "id", required = true) Long id) {
		return categoryService.deleteById(id);
	}

	@GetMapping("categories")
	@ResponseBody
	public Map<String, Object> getAll(@RequestParam(name = "page", defaultValue = "0") Integer pageIndex,
			@RequestParam(name = "size", defaultValue = "10") Integer size,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "active", required = false) Boolean active) {

		Pageable pageable = PageRequest.of(pageIndex, size);

		Page<Category> page;

		if (active != null) {
			page = categoryRepo.findByNameContainsAndActiveEquals(name, active, pageable);
		} else {
			page = categoryRepo.findByNameContains(name, pageable);
		}

		Page<CategoryDto> dtoPage = page.map(cate -> mapper.map(cate, CategoryDto.class));

		Map<String, Object> rs = new HashMap<String, Object>();
		rs.put("categories", dtoPage.getContent());
		rs.put("totalPages", dtoPage.getTotalPages());
		rs.put("currentPage", dtoPage.getNumber());

		return rs;
	}

	@DeleteMapping("categories/{id}/deactivate")
	@ResponseBody
	public void deactivate(@PathVariable Long id) {
		Category category = categoryRepo.getById(id);
		if (category != null) {
			category.setActive(false);
			category.setUpdatedAt(Calendar.getInstance().getTime());
			categoryRepo.save(category);
		}
	}

	@PutMapping("categories/{id}/activate")
	@ResponseBody
	public CategoryDto activate(@PathVariable Long id) {
		Category category = categoryRepo.getById(id);
		if (category != null) {
			category.setActive(true);
			category.setUpdatedAt(Calendar.getInstance().getTime());
			category = categoryRepo.save(category);
			return mapper.map(category, CategoryDto.class);
		}
		return null;
	}

}
