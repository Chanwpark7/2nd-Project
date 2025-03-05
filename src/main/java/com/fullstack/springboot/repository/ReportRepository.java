package com.fullstack.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Report;
import com.fullstack.springboot.entity.ReportHistory;

public interface ReportRepository extends JpaRepository<Report, Long> {

	@Query("Select r, rh, rf from Report r "
			+ "left join ReportHistory rh on r = rh.report "
			+ "left join r.reportFiles rf on rf.ord = 0 "
			+ "where rh.receiver = :receiver "
			+ "AND rh.rhNo = (SELECT MIN(subRh.rhNo) FROM ReportHistory subRh WHERE subRh.report = r and subRh.status = '대기' ) "
			+ "order by r.reportStatus desc")
	Page<Object[]> selectReceivedList(@Param("receiver") Employees receiver, Pageable pageable);
	
	@Query("Select r, rh, rf from Report r "
			+ "left join ReportHistory rh on r = rh.report "
			+ "left join r.reportFiles rf on rf.ord = 0 "
			+ "where rh.receiver = :receiver "
			+ "order by r.reportStatus desc")
	Page<Object[]> selectReceivedAllList(@Param("receiver") Employees receiver, Pageable pageable);
	
	@Query("Select r, rh, rf from Report r "
			+ "left join ReportHistory rh on r = rh.report "
			+ "left join r.reportFiles rf on rf.ord = 0 "
			+ "where r.sender = :sender "
			+ "AND (rh.receiver IS NULL OR rh.receiver <> r.sender) "
			+ "AND (rh.rhNo = (SELECT MIN(subRh.rhNo) FROM ReportHistory subRh WHERE subRh.report = r and subRh.status = '대기' ) "
			+ "or rh.rhNo = (SELECT MIN(subRh.rhNo) FROM ReportHistory subRh WHERE subRh.report = r and r.reportStatus = '완료' )) ")
	Page<Object[]> selectSentList(@Param("sender") Employees sender, Pageable pageable);
}
