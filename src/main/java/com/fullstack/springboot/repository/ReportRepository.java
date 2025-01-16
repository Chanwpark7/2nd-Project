package com.fullstack.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

	@Query("Select r, rf from Report r left join r.reportFiles rf where r.deadLine >= curdate() and r.receiver = :receiver and rf.ord = 0")
	Page<Object[]> selectReceivedList(@Param("receiver") Employees receiver, Pageable pageable);
	
	@Query("Select r, rf from Report r left join r.reportFiles rf where r.sender = :sender and rf.ord = 0")
	Page<Object[]> selectSentList(@Param("sender") Employees sender, Pageable pageable);
}
