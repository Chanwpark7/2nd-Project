package com.fullstack.springboot.service.deptinfo;

import java.util.List;

import com.fullstack.springboot.dto.DeptInfoDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.DeptInfo;

public interface DeptInfoService {

	public DeptInfoDTO createOrModifyDept(DeptInfoDTO deptInfoDTO);
	
	public void deleteDept(Long deptNo);
	
	public List<DeptInfoDTO> getDeptList();
	
	public DeptInfoDTO getOne(Long deptNo);
	
	public PageResponseDTO<EmployeesDTO> getEmployeesListPageByDeptNo(PageRequestDTO pageRequestDTO, Long deptNo);
	
	default DeptInfo dtoToEntity(DeptInfoDTO deptInfoDTO) {
		DeptInfo deptInfo = DeptInfo.builder()
				.deptNo(deptInfoDTO.getDeptNo())
				.deptName(deptInfoDTO.getDeptName())
				.deptAddress(deptInfoDTO.getDeptAddress())
				.phoneNo(deptInfoDTO.getPhoneNo())
				.build();
		
		return deptInfo;
	}
	
	default DeptInfoDTO entityToDto(DeptInfo deptInfo) {
		DeptInfoDTO deptInfoDTO = DeptInfoDTO.builder()
				.deptNo(deptInfo.getDeptNo())
				.deptName(deptInfo.getDeptName())
				.deptAddress(deptInfo.getDeptAddress())
				.phoneNo(deptInfo.getPhoneNo())
				.build();
		
		return deptInfoDTO;
	}
}
