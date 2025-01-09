package com.fullstack.springboot.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.entity.Employees;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {

	@Query("select e from Employees e where e.empNo =:empNo")
	public Optional<Employees> getEmpNo(@Param("empNo")Long empNO);
	
	@Query("select new com.fullstack.springboot.dto.EmployeesDTO(e.empNo, e.firstName, e.lastName, e.mailAddress, e.phoneNum) from Employees e ")
	public Page<EmployeesDTO> empAllList(Pageable pageable);
	
	@Query("select new com.fullstack.springboot.dto.EmployeesDTO(e.empNo, e.firstName, e.lastName, e.mailAddress, e.phoneNum) from Employees e "
			+ "where e.firstName like :firstname% or e.lastName like :lastName%")
	public Page<EmployeesDTO> empLikstList(Pageable pageable);
	
	@Query("Select new com.fullstack.springboot.dto.EmployeesDTO(emp) from Employees emp")
	Page<EmployeesDTO> getEmployeesList(Pageable pageable);

	@Query("Select distinct new com.fullstack.springboot.dto.EmployeesDTO(emp) "
			+ "from Employees emp "
			+ "left join Job j on emp.job = j "
			+ "where j.jobNo = :jobNo")
	Page<EmployeesDTO> getEmployeesListByJobNo(Pageable pageable, @Param("jobNo") Long jobNo);
	
	@Query("Select distinct new com.fullstack.springboot.dto.EmployeesDTO(emp) "
			+ "from Employees emp "
			+ "left join DeptInfo di on emp.deptInfo = di "
			+ "where di.deptNo = :deptNo")
	Page<EmployeesDTO> getEmployeesListByDeptNo(Pageable pageable, @Param("deptNo") Long deptNo);
	
	@Query("Select emp, j, di, al, cm "
			+ "from Employees emp "
			+ "left join Job j on emp.job = j "
			+ "left join DeptInfo di on emp.deptInfo = di "
			+ "left join AnnualLeave al on al.employees = emp "
			+ "left join Commute cm on cm.employees = emp "
			+ "where emp.empNo = :empNo")
	Object[] getOneByEmpNo(@Param("empNo") Long empNo);
}
