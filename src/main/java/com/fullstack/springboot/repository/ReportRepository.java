package com.fullstack.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

	@Query("Select r, rf from Report r left join ReportFiles rf where r.receiver = :receiver")
	Page<Object[]> selectList(@Param("receiver") Long receiver, Pageable pageable);
}
