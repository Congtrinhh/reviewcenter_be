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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.trinhquycong.reviewcenter.dto.CenterDto;
import com.trinhquycong.reviewcenter.dto.CenterSearchDto;
import com.trinhquycong.reviewcenter.entity.Category;
import com.trinhquycong.reviewcenter.entity.Center;
import com.trinhquycong.reviewcenter.repo.CategoryRepo;
import com.trinhquycong.reviewcenter.repo.CenterRepo;
import com.trinhquycong.reviewcenter.service.CenterService;
import com.trinhquycong.reviewcenter.specification.CenterSpecification;

@RestController
@RequestMapping("admin")
public class CenterAdminController {

	@Autowired
	private CenterService centerService;

	@Autowired
	private CenterRepo centerRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	/**
	 * nhớ set centerSize của dto, vì khác tên với entity(size#centerSize) 
	 */
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("centers")
	public ResponseEntity<CenterDto> createOne(@RequestBody CenterDto centerDto) throws RuntimeException {

		if (centerDto == null) {
			throw new RuntimeException("center mustn't be null");
			// or send err msg back to client
		}

		Center center = toEntity(centerDto);
		center.setCreatedAt(Calendar.getInstance().getTime());

		center = centerRepo.save(center);

		centerDto = modelMapper.map(center, CenterDto.class);

		ResponseEntity<CenterDto> responseEntity = new ResponseEntity<CenterDto>(centerDto, HttpStatus.CREATED);
		return responseEntity;
	}

	private Center toEntity(CenterDto dto) {
		// TODO Auto-generated method stub
		Center center = modelMapper.map(dto, Center.class);
		center.setSize(dto.getCenterSize());
		
		Category category = categoryRepo.getById(dto.getCategoryId());

		if (category == null) {
			throw new RuntimeException("category in center mustn't be null");
		}

		center.setCategory(category);
		return center;
	}
	
	private CenterDto toDto(Center center) {
		// TODO Auto-generated method stub
		CenterDto dto = modelMapper.map(center, CenterDto.class);
		dto.setCenterSize(center.getSize());
		return dto;
	}

	@GetMapping("centers/{id}")
	public ResponseEntity<CenterDto> getOne(@PathVariable Long id) {

		Center center = centerRepo.getById(id);

		if (center == null) {
			throw new RuntimeException("center mustn't be null");
		}

		// convert to dto
		CenterDto centerDto = toDto(center);

		ResponseEntity<CenterDto> responseEntity = new ResponseEntity<CenterDto>(centerDto, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("centers")
	public ResponseEntity<CenterDto> updateOne(@RequestBody CenterDto centerDto) {

		Center center = toEntity(centerDto);

		center = centerRepo.save(center);

		// để trả về client
		centerDto = toDto(center);

		return new ResponseEntity<CenterDto>(centerDto, HttpStatus.OK);
	}

	@DeleteMapping("centers/{id}")
	public ResponseEntity<String> deleteOne(@PathVariable("id") Long id) {
		String resultMsg = centerService.deleteById(id);
		return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
	}

	@GetMapping("centers")
	@ResponseBody
	public Map<String, Object> getAll(
			@RequestParam(name = "page", defaultValue = "0") Integer pageIndex,
			@RequestParam(name = "size", defaultValue = "10") Integer size,
			@Valid CenterSearchDto filter) {

		Pageable pageable = PageRequest.of(pageIndex, size);

		Page<Center> page;
		
		//Specification<Center> specs = CenterSpecs.nameContains(" "); 
		//page = centerRepo.findAll(specs, pageable);
		
		Specification<Center> specs = new CenterSpecification(filter);
		page = centerRepo.findAll(specs, pageable);
		
		Page<CenterDto> dtoPage = page.map(this::toDto);
		
		Map<String, Object> rs = new HashMap<String, Object>();
		rs.put("categories", dtoPage.getContent());
		rs.put("totalPages", dtoPage.getTotalPages());
		rs.put("currentPage", dtoPage.getNumber());
		
		return rs;
	}
	
	@DeleteMapping("center/{id}/deactivate")
	@ResponseBody
	public void deactivate(@PathVariable Long id) {
		Center center = centerRepo.getById(id);
		if (center!=null) {
			center.setActive(false);
			centerRepo.save(center);
		}
	}
	
	@PutMapping("center/{id}/activate")
	@ResponseBody
	public CenterDto activate(@PathVariable Long id) {
		Center center = centerRepo.getById(id);
		if (center!=null) {
			center.setActive(true);
			center=  centerRepo.save(center);
			return modelMapper.map(center, CenterDto.class);
		}
		return null;
	}
	
	
}
