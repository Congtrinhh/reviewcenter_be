package com.trinhquycong.reviewcenter.controller.admin;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trinhquycong.reviewcenter.dto.ResourceDto;
import com.trinhquycong.reviewcenter.entity.Resource;
import com.trinhquycong.reviewcenter.repo.ResourceRepo;
import com.trinhquycong.reviewcenter.service.ResourceService;

@RestController
@RequestMapping("admin")
public class ResourceAdminController {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ResourceRepo resourceRepo;
	
	@Autowired
	private ResourceService resourceService;
	
	@PostMapping("resources")
	@ResponseBody
	public ResourceDto createOne(@RequestBody(required=true) ResourceDto resourceDto) {
		Resource resource = mapper.map(resourceDto, Resource.class);
		
		resource.setCreatedAt(Calendar.getInstance().getTime());
		resource = resourceRepo.save(resource);
		
		return mapper.map(resource, ResourceDto.class);
	}
	
	@GetMapping("resources/{id}")
	@ResponseBody
	public ResourceDto getOne(@PathVariable(value="id", required=true) Long id) {
		Resource resource = resourceRepo.getById(id);
		
		return mapper.map(resource, ResourceDto.class);
	}
	
	@PutMapping("resources")
	@ResponseBody
	public ResourceDto updateOne(@RequestBody(required=true) ResourceDto resourceDto) {
		Resource resource = mapper.map(resourceDto, Resource.class);
		resource.setUpdatedAt(Calendar.getInstance().getTime());
		
		resource = resourceRepo.save(resource);
		
		return mapper.map(resource, ResourceDto.class);
	}
	
	@DeleteMapping("resources/{id}")
	public String deleteOne(@PathVariable(value="id", required=true) Long id) {
		return resourceService.deleteById(id);
	}
	
	@GetMapping("resources")
	@ResponseBody
	public List<ResourceDto> getAll(){
		List<Resource> resources = resourceRepo.findAll();
		List<ResourceDto> dtos = resources.stream().map(resource-> {
			return mapper.map(resource, ResourceDto.class);
		}).collect(Collectors.toList());
		return dtos;
	}
}
