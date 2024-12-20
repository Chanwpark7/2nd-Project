package com.fullstack.springboot.service.commute;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.CommuteDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.entity.Commute;
import com.fullstack.springboot.repository.CommuteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CommuteServiceImpl implements CommuteService {

	private final CommuteRepository commuteRepository;
	
	@Override
	public Long addCommute(CommuteDTO commuteDTO) {
		
		commuteRepository.save(dtoToEntity(commuteDTO));
		
		return commuteDTO.getEmpNo();
	}
	
	@Override
	public Long checkOut(Long empNo) {

		CommuteDTO commuteDTO = commuteRepository.whenCheckingOut(empNo);
		
		if(commuteDTO == null) {
			return -1L;
		};
		
		Commute commute = dtoToEntity(commuteDTO);
		
		commute.changeCheckOut(LocalDateTime.now());
		
		commute.changeCommNo(commuteDTO.getCommNo());
		
		commuteRepository.save(commute);
		
		return commute.getEmployees().getEmpNo();
	}
	
	@Override
	public Page<CommuteDTO> getListCommute(Long empNo, PageRequestDTO pageRequestDTO) {

		Pageable pageable = pageRequestDTO.getPageable(Sort.by("commNo").descending());
		
		Page<CommuteDTO> page = commuteRepository.getListComm(empNo, pageable);
		
		return page;
	}
}
