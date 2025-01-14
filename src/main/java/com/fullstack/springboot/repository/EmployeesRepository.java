package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.EntityGraph;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.EmployeesAuthDTO;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.dto.CommuteDTO;
import com.fullstack.springboot.dto.EmployeesDTO;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
  
  @EntityGraph(attributePaths = "roleSet")
	@Query("select emp from Employees emp where emp.mailAddress = :email")
	Employees getByEmail(@Param("email") String email);

	@Query("select e from Employees e where e.empNo =:empNo")
	public Optional<Employees> getEmpNo(@Param("empNo")Long empNo);
	
	@Query("select new com.fullstack.springboot.dto.EmployeesDTO(e) from Employees e ")
	public Page<EmployeesDTO> empAllList(Pageable pageable);
	
	@Query("select distinct new com.fullstack.springboot.dto.EmployeesDTO(e) from Employees e"
			+ " inner join CompanyChatMember cm on e.empNo =:empNo")
	public EmployeesDTO empFind(@Param("empNo") Long empNo);
  
	@Query("select new com.fullstack.springboot.dto.EmployeesDTO(e) from Employees e "
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
	
	@Query("Select new com.fullstack.springboot.dto.CommuteDTO(cm) "
			+ "from Commute cm "
			+ "where cm.employees = :empNo")
	Page<CommuteDTO> getCommuteByEmpNo(Pageable pageable, @Param("empNo")Long empNo);
	
	@Query("Select FUNCTION('MAX',e.empNo) from Employees e")
	Object getMaxEmpNo();
}
