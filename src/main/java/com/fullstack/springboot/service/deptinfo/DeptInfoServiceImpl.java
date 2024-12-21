package com.fullstack.springboot.service.deptinfo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.DeptInfoDTO;
import com.fullstack.springboot.repository.DeptInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class DeptInfoServiceImpl implements DeptInfoService {

	private final DeptInfoRepository deptInfoRepository;
	
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

}
