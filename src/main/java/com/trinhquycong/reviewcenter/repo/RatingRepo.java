package com.trinhquycong.reviewcenter.repo;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.trinhquycong.reviewcenter.entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, Long>, JpaSpecificationExecutor<Rating> {

	@Transactional
	@Modifying
	@Query(value = "update rating set rate=:rate, comment=:comment, center_id=:centerId, user_id=:userId, updated_at=:updatedAt  where id=:id", nativeQuery = true)
	void update(@Param("id") Long id, @Param("rate") Integer rate, @Param("comment") String comment,
			@Param("userId") Long userId, @Param("centerId") Long centerId, @Param("updatedAt") Date updatedAt);
	
	Page<Rating> findByUserIdEquals(Long userId, Pageable pageable);
	
	Page<Rating> findByUserIdEqualsAndRateIn(Long userId, Collection<?> rates, Pageable pageable);
	
	Page<Rating> findByCenterIdEquals(Long centerId, Pageable pageable);
}
