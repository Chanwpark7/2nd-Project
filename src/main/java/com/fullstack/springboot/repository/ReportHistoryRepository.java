package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Report;
import com.fullstack.springboot.entity.ReportHistory;

public interface ReportHistoryRepository extends JpaRepository<ReportHistory, Long> {

	@Query("SELECT MIN(rh.rhNo) FROM ReportHistory rh "
			+ "where rh.status = '대기' "
			+ "and rh.report = :report")
	Long getOneRH(@Param("report") Report report);
	
	@Query("SELECT rh FROM ReportHistory rh "
			+ "where rh.status = '대기' "
			+ "and rh.report = :report")
	List<ReportHistory> getRHList(@Param("report") Report report);
}
