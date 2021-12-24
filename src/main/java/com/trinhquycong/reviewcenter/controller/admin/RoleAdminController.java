package com.trinhquycong.reviewcenter.controller.admin;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import com.trinhquycong.reviewcenter.dto.RoleDto;
import com.trinhquycong.reviewcenter.entity.Role;
import com.trinhquycong.reviewcenter.repo.RoleRepo;
import com.trinhquycong.reviewcenter.service.RoleService;

@RestController
@RequestMapping("admin")
public class RoleAdminController {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("roles")
	@ResponseBody
	public RoleDto createOne(@RequestBody @Valid RoleDto roleDto) {
		Role role = mapper.map(roleDto, Role.class);
		
		role.setCreatedAt(Calendar.getInstance().getTime());
		role = roleRepo.save(role);
		
		return mapper.map(role, RoleDto.class);
	}
	
	@GetMapping("roles/{id}")
	@ResponseBody
	public RoleDto getOne(@PathVariable Long id) {
		Role role = roleRepo.getById(id);
		
		return mapper.map(role, RoleDto.class);
	}
	
	@PutMapping("roles")
	@ResponseBody
	public RoleDto updateOne(@RequestBody @Valid RoleDto roleDto) {
		Role role = mapper.map(roleDto, Role.class);
		role.setUpdatedAt(Calendar.getInstance().getTime());
		
		role = roleRepo.save(role);
		
		return mapper.map(role, RoleDto.class);
	}
	
	@DeleteMapping("roles/{id}")
	@ResponseBody
	public String deleteOne(@PathVariable Long id) {
		return roleService.deleteById(id);
	}
	
	@GetMapping("roles")
	@ResponseBody
	public Map<String, Object> getAll(
			@RequestParam(name = "page", defaultValue = "0") Integer pageIndex,
			@RequestParam(name = "size", defaultValue = "10") Integer size){
		
		Page<RoleDto> dtoPage = roleRepo.findAll(PageRequest.of(pageIndex, size))
				.map(role->mapper.map(role, RoleDto.class));
		
		Map<String, Object> rs = new HashMap<String, Object>();
		
		rs.put("roles", dtoPage.getContent());
		rs.put("totalPages", dtoPage.getTotalPages());
		rs.put("currentPage", dtoPage.getNumber());
		return rs;
	}
}
