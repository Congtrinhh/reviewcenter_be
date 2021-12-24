package com.trinhquycong.reviewcenter.controller.admin;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trinhquycong.reviewcenter.dto.AdminRegisterDto;
import com.trinhquycong.reviewcenter.dto.UserDto;
import com.trinhquycong.reviewcenter.dto.UserSearchDto;
import com.trinhquycong.reviewcenter.entity.Role;
import com.trinhquycong.reviewcenter.entity.User;
import com.trinhquycong.reviewcenter.repo.RoleRepo;
import com.trinhquycong.reviewcenter.repo.UserRepo;
import com.trinhquycong.reviewcenter.service.UserService;
import com.trinhquycong.reviewcenter.specification.UserSpecification;

@RestController
@RequestMapping("admin")
/**
 * for all user (admin, MANAGER, user)
 * 
 * @author trinh
 *
 */
public class UserAdminController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * for administrators - not user
	 * @param dto
	 * @return
	 */
	@PostMapping("users")
	@ResponseBody
	public AdminRegisterDto createOne(@Validated @RequestBody AdminRegisterDto dto) {
		Set<Role> roles = dto.getRoles().stream()
				.map(roleName->roleRepo.findByName(roleName))
				.collect(Collectors.toSet());
		
		User user = mapper.map(dto, User.class);
		user.setRoles(roles);
		user.setCreatedAt(Calendar.getInstance().getTime());
		user.setActive(true);
		
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		user = userRepo.save(user);
		dto = mapper.map(user, AdminRegisterDto.class);
		return dto;
	}

	
	@GetMapping("users/{id}")
	@ResponseBody
	public UserDto getOne(@PathVariable Long id) {
		User user = userRepo.getById(id);
		if (user.getId() == null) {
			throw new RuntimeException("user mustn't be null");
		}

		UserDto dto = toDto(user);

		return dto;
	}

	private Set<String> getRoleNames(User user) {
		return user.getRoles()
				.stream().map(role -> role.getName())
				.collect(Collectors.toSet());
	}
	
	private UserDto toDto(User user) {
		UserDto dto = mapper.map(user, UserDto.class);
		
		Set<String> roles = getRoleNames(user);
		dto.setRoles(roles);
		
		return dto;
	}
	
	/*
	 * private User toUserEntity(UserDto dto) { User user = mapper.map(dto,
	 * User.class); Set<Role> roles = dto.getRoles().stream()
	 * .map(roleName->roleRepo.findByName(roleName)) .collect(Collectors.toSet());
	 * user.setRoles(roles);
	 * 
	 * return user; }
	 */

	// update 1 phần
	@PatchMapping("users/{id}")
	@ResponseBody
	public UserDto updatePartially(@RequestBody(required = true) Map<String, Object> updates,
			@PathVariable(required = true) Long id) {
		User updatedUser = userService.save(updates, id);

		return toDto(updatedUser);
	}

	/**
	 * delete completely entity from DB
	 * @param id
	 * @return
	 */
	@DeleteMapping("users/{id}")
	@ResponseBody
	public String deleteOne(@PathVariable(required = true) Long id) {
		return userService.deleteById(id);
	}

	/**
	 * page param: zero-based
	 * @param roleName
	 * @param roleName
	 * @return
	 */
	@GetMapping("users")
	@ResponseBody
	public Map<String, Object> getAll(
			@RequestParam(name = "page", defaultValue = "0") Integer pageIndex,
			@RequestParam(name = "size", defaultValue = "10") Integer size,
			UserSearchDto filter) {
		
		Pageable pageable = PageRequest.of(pageIndex, size);
		
		Specification<User> specs = new UserSpecification(filter);
		Page<User> page = userRepo.findAll(specs, pageable);

		Page<UserDto> dtoPage = page.map(this::toDto);
		
		Map<String, Object> rs = new HashMap<String, Object>();
		
		rs.put("users", dtoPage.getContent());
		rs.put("totalPages", dtoPage.getTotalPages());
		rs.put("currentPage", dtoPage.getNumber());
		return rs;
	}
	
	/**
	 * delete (deactivate)
	 */
	@DeleteMapping("users/{id}/deactivate")
	@ResponseBody
	public void deactivate(@PathVariable Long id) {
		User user = userRepo.getById(id);
		if (user!=null) {
			user.setActive(false);
			user.setUpdatedAt(Calendar.getInstance().getTime());
			userRepo.save(user);
		}
	}
	
	/**
	 * activate again
	 * @param id
	 * @return
	 */
	@PutMapping("user/{id}/activate")
	@ResponseBody
	public UserDto activate(@PathVariable Long id) {
		User user = userRepo.getById(id);
		if (user!=null) {
			user.setActive(true);
			user.setUpdatedAt(Calendar.getInstance().getTime());
			user = userRepo.save(user);
			return toDto(user);
		}
		return null;
	}

}
