package com.fullstack.springboot.service.deptinfo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.DeptInfoDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.JobDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.DeptInfo;
import com.fullstack.springboot.entity.Job;
import com.fullstack.springboot.repository.DeptInfoRepository;
import com.fullstack.springboot.repository.EmployeesRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class DeptInfoServiceImpl implements DeptInfoService {

	private final DeptInfoRepository deptInfoRepository;
	
	private final EmployeesRepository employeesRepository;
	
	@Override
	public DeptInfoDTO createOrModifyDept(DeptInfoDTO deptInfoDTO) {

		deptInfoRepository.save(dtoToEntity(deptInfoDTO));
		
		return deptInfoDTO;
	}

	@Override
	public void deleteDept(Long deptNo) {
		deptInfoRepository.delete(deptInfoRepository.findById(deptNo).get());
	}

	@Override
	public List<DeptInfoDTO> getDeptList() {
		
		return deptInfoRepository.getDeptList();
	}

	@Override
	public DeptInfoDTO getOne(Long deptNo) {

		DeptInfo deptInfo = deptInfoRepository.findById(deptNo).get();
		
		return entityToDto(deptInfo);
	}
	
	@Override
	public PageResponseDTO<EmployeesDTO> getEmployeesListPageByDeptNo(PageRequestDTO pageRequestDTO, Long deptNo) {

		Pageable pageable = pageRequestDTO.getPageable(Sort.by("empNo").ascending());
		
		Page<EmployeesDTO> page = employeesRepository.getEmployeesListByDeptNo(pageable, deptNo);
		
		List<EmployeesDTO> dtoList = page.get().toList();
		
		long totalCount = page.getTotalElements();
		
		return PageResponseDTO.<EmployeesDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}
}
