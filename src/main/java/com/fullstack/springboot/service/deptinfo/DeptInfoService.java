package com.fullstack.springboot.service.deptinfo;

import java.util.List;

import com.fullstack.springboot.dto.DeptInfoDTO;
import com.fullstack.springboot.entity.DeptInfo;

public interface DeptInfoService {

	public DeptInfoDTO createOrModifyDept(DeptInfoDTO deptInfoDTO);
	
	public void deleteDept(Long deptNo);
	
	public List<DeptInfoDTO> getDeptList();
	
	default DeptInfo dtoToEntity(DeptInfoDTO deptInfoDTO) {
		DeptInfo deptInfo = DeptInfo.builder()
				.deptNo(deptInfoDTO.getDeptNo())
				.deptName(deptInfoDTO.getDeptName())
				.deptAddress(deptInfoDTO.getDeptAddress())
				.phoneNo(deptInfoDTO.getPhoneNo())
				.build();
		
		return deptInfo;
	}
}
