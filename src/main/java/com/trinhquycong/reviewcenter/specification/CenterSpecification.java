package com.trinhquycong.reviewcenter.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.trinhquycong.reviewcenter.dto.CenterSearchDto;
import com.trinhquycong.reviewcenter.entity.Category;
import com.trinhquycong.reviewcenter.entity.Center;

/**
 * <p>Using JPA Criteria API for filtering entity</p>
 * <p>Mainly used in getAll method in the corresponding controller</p>
 * <p>Don't forget to inject the search object</p>
 * @author trinh
 *
 */
public class CenterSpecification implements Specification<Center> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CenterSearchDto search;
	
	public CenterSpecification(CenterSearchDto search) {
		this.search = search;
	}

	@Override
	public Predicate toPredicate(Root<Center> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		
		Join<Center, Category> category = root.join("category");
		
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (search.getCategoryId()!=null&&search.getCategoryId()!=0) {
			predicates.add(cb.equal(category.get("id"), search.getCategoryId()));
		}
		if (search.getActive()!=null) {
			predicates.add(cb.equal(root.get("active"), search.getActive()));
		}
		if (search.getName()!=null) {
			predicates.add(cb.like(root.get("name"), "%" + search.getName() + "%"));
		}
		if (search.getAddress()!=null) {
			predicates.add(cb.like(root.get("address"), "%" +search.getAddress()+ "%" ));
		}
		if (search.getDescription()!=null) {
			predicates.add(cb.like(root.get("description"), "%" +search.getDescription()+ "%" ));
		}
		if (search.getSizeMin()!=null && search.getSizeMax()!=null) {
			predicates.add(cb.between(root.get("size"), search.getSizeMin(), search.getSizeMax()));
		}		
		
		Predicate[] p = new Predicate[predicates.size()];
		
		return cb.and(predicates.toArray(p));
	}

}
