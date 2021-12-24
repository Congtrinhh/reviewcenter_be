package com.trinhquycong.reviewcenter.controller.admin;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

import com.trinhquycong.reviewcenter.dto.RatingDto;
import com.trinhquycong.reviewcenter.dto.RatingSearchDto;
import com.trinhquycong.reviewcenter.entity.Center;
import com.trinhquycong.reviewcenter.entity.Rating;
import com.trinhquycong.reviewcenter.entity.User;
import com.trinhquycong.reviewcenter.repo.CenterRepo;
import com.trinhquycong.reviewcenter.repo.RatingRepo;
import com.trinhquycong.reviewcenter.repo.UserRepo;
import com.trinhquycong.reviewcenter.service.RatingService;
import com.trinhquycong.reviewcenter.specification.RatingSpecification;

@RestController
@RequestMapping("admin")
public class RatingAdminController {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CenterRepo centerRepo;

	@Autowired
	private RatingRepo ratingRepo;
	
	@Autowired
	private RatingService ratingService;

	@PostMapping("ratings")
	@ResponseBody
	public RatingDto createOne(@RequestBody RatingDto ratingDto) {

		User user = userRepo.getById(ratingDto.getUserId());

		Center center = centerRepo.getById(ratingDto.getCenterId());

		if (user.getId() == null || center.getId() == null) {
			throw new RuntimeException("user or center mustn't be null");
		}

		Rating rating = mapper.map(ratingDto, Rating.class);
		rating.setUser(user);
		rating.setCenter(center);
		rating.setCreatedAt(Calendar.getInstance().getTime());

		try {
			rating = ratingRepo.save(rating);
		} catch (Exception e) {
			throw new RuntimeException("a user can't comment twice");
		}

		return mapper.map(rating, RatingDto.class);
	}

	@GetMapping("ratings/{id}")
	@ResponseBody
	public RatingDto getOne(@PathVariable Long id) {
		Rating rating = ratingRepo.getById(id);
		if (rating.getId() == null) {
			throw new RuntimeException("rating mustn't be null");
		}
		return mapper.map(rating, RatingDto.class);
	}

	@PutMapping("ratings")
	@ResponseBody
	public RatingDto updateOne(@RequestBody RatingDto dto) {
		
		Rating ratingFromDB = ratingRepo.getById(dto.getId());
		
		if (ratingFromDB.getUser().getId()==dto.getUserId()&&ratingFromDB.getCenter().getId()==dto.getCenterId()) {
			ratingRepo.update(dto.getId(), dto.getRate(), dto.getComment(), dto.getUserId(), dto.getCenterId(), Calendar.getInstance().getTime());
			return dto;			
		} 
		
		Rating rating = mapper.map(dto, Rating.class);
		User user = userRepo.getById(dto.getUserId());
		Center center = centerRepo.getById(dto.getCenterId());
		rating.setCenter(center);
		rating.setUser(user);
		rating.setUpdatedAt(Calendar.getInstance().getTime());
		
		rating = ratingRepo.save(rating);
		
		return mapper.map(rating, RatingDto.class);
	}
	
	@DeleteMapping("ratings/{id}")
	@ResponseBody
	public String deleteOne(@PathVariable Long id) {
		return ratingService.deleteById(id);
	}
	
	@GetMapping("ratings")
	@ResponseBody
	public Map<String, Object> getAll(
			@RequestParam(name = "page", defaultValue = "0") Integer pageIndex,
			@RequestParam(name = "size", defaultValue = "10") Integer size,
			@Valid RatingSearchDto filter){
		
		Pageable pageable = PageRequest.of(pageIndex, size);
		
		Specification<Rating> specs = new RatingSpecification(filter);
		
		Page<Rating> page = ratingRepo.findAll(specs, pageable);
		
		
		Page<RatingDto> dtoPage = page.map(rating->mapper.map(rating, RatingDto.class));
		
		Map<String, Object> rs = new HashMap<String, Object>();
		rs.put("ratings", dtoPage.getContent());
		rs.put("totalPages", dtoPage.getTotalPages());
		rs.put("currentPage", dtoPage.getNumber());
		
		return rs;
	}

}
