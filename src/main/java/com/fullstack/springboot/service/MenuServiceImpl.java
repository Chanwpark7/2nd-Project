package com.fullstack.springboot.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.MenuDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.Menu;
import com.fullstack.springboot.repository.MenuRepositoy;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@NoArgsConstructor
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuRepositoy menuRepositoy;
	
	@Override
	public MenuDTO getTodayMenu(LocalDate menuDate) {
		return menuRepositoy.getTodayMenu(menuDate);
	}

	
	@Override
	public void addMenu(MenuDTO menuDTO) {
		log.error("!!!");
		menuRepositoy.save(dtoToEntity(menuDTO));
		log.error("성공");
	}


	@Override
	public PageResponseDTO<MenuDTO> getMenuList(PageRequestDTO pageRequestDTO) {
		
		if(pageRequestDTO.getPage() <0 ) {
			pageRequestDTO.setPage(0);
		}
		
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("menuDate").descending());
		Page<MenuDTO> page = menuRepositoy.getMenuList(pageable);
		List<MenuDTO> dtoList = page.get().toList();
		long totalCnt = page.getTotalElements();
		return PageResponseDTO.<MenuDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCnt)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}


	@Override
	public MenuDTO getOne(long menuNo) {
		log.warn("이건 ???????");
		return entityToDto(menuRepositoy.findById(menuNo).get());
	}


	@Override
	public void removeMenu(long menuNo) {
		menuRepositoy.deleteById(menuNo);
	}


	@Transactional
	@Override
	public void modMenu(MenuDTO menuDTO) {
		Menu menu = menuRepositoy.getOne(menuDTO.getMenuNo());
		
		if(menu != null) {
			menu.changeFirSideDish(menuDTO.getFirSideDish());
			menu.changeSecSideDish(menuDTO.getSecSideDish());
			menu.changeThirdSideDish(menuDTO.getThirdSideDish());
			menu.changeMainMenu(menuDTO.getMainMenu());
			menu.changeDessert(menuDTO.getDessert());
			menu.changeMenuDate(menuDTO.getMenuDate());
			menuRepositoy.save(menu);
		}
		
	}



	

}
