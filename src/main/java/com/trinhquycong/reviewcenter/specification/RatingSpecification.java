package com.trinhquycong.reviewcenter.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.trinhquycong.reviewcenter.dto.RatingSearchDto;
import com.trinhquycong.reviewcenter.entity.Center;
import com.trinhquycong.reviewcenter.entity.Rating;
import com.trinhquycong.reviewcenter.entity.User;

public class RatingSpecification implements Specification<Rating> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RatingSearchDto search;
	
	public RatingSpecification(RatingSearchDto search) {
		this.search = search;
	}

	@Override
	public Predicate toPredicate(Root<Rating> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		Join<Rating, Center> center = root.join("center");
		
		Join<Rating, User> user = root.join("user");
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (search.getCenterId()!=null) {
			predicates.add(cb.equal(center.get("id"), search.getCenterId()));
		}
		if (search.getUserId()!=null) {
			predicates.add(cb.equal(user.get("id"), search.getUserId()));
		}
		if (search.getRate()!=null) {
			predicates.add(cb.equal(root.get("rate"), search.getRate()));
		}
		if (search.getActive()!=null) {
			predicates.add(cb.equal(root.get("active"), search.getActive()));
		}
		
		Predicate[] p = new Predicate[predicates.size()];
		
		return cb.and(predicates.toArray(p));
	}

}
