package com.trinhquycong.reviewcenter.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.trinhquycong.reviewcenter.dto.UserSearchDto;
import com.trinhquycong.reviewcenter.entity.Role;
import com.trinhquycong.reviewcenter.entity.User;

public class UserSpecification implements Specification<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserSearchDto search;
	
	public UserSpecification(UserSearchDto search) {
		super();
		this.search = search;
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		Join<User, Role> roles = root.join("roles");
		
		if (search.getActive()!=null) {
			predicates.add(cb.equal(root.get("active"), search.getActive()));
		}
		if (search.getDisplayName()!=null) {
			predicates.add(cb.like(root.get("displayName"), "%"+search.getDisplayName()+"%"));
		}
		if (search.getProvider()!=null) {
			predicates.add(cb.equal(cb.upper(root.get("provider")), search.getProvider().toUpperCase()));
		}
		if (search.getRoleId()!=null) {
			predicates.add(cb.equal(roles.get("id"), search.getRoleId()));
		}
		if (search.getRoleName()!=null) {
			predicates.add(cb.equal(cb.upper(roles.get("name")), search.getRoleName().toUpperCase()));
		}
		
		Predicate[] p = new Predicate[predicates.size()];
		
		return cb.and(predicates.toArray(p));
	}
	
}
