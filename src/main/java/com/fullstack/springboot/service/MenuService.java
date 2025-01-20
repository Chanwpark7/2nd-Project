package com.fullstack.springboot.service;

import java.time.LocalDate;
import java.util.List;

import com.fullstack.springboot.dto.MenuDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Menu;


public interface MenuService {

	public MenuDTO getTodayMenu(LocalDate menuDate);
	
	public void addMenu(MenuDTO menuDTO);
	
	default Menu dtoToEntity(MenuDTO menuDTO) {
		Employees employees = Employees.builder().empNo(menuDTO.getEmpNo()).build();
		Menu menu = Menu.builder().
				dessert(menuDTO.getDessert())
				.firSideDish(menuDTO.getFirSideDish())
				.secSideDish(menuDTO.getSecSideDish())
				.mainMenu(menuDTO.getMainMenu())
				.thirdSideDish(menuDTO.getThirdSideDish())
				.employees(employees)
				.menuDate(menuDTO.getMenuDate())
				.build();
		return menu;
	}
	
	default MenuDTO entityToDto(Menu menu) {
		MenuDTO dto = MenuDTO.builder()
				.menuNo(menu.getMenuNo())
				.mainMenu(menu.getMainMenu())
				.firSideDish(menu.getFirSideDish())
				.secSideDish(menu.getSecSideDish())
				.thirdSideDish(menu.getThirdSideDish())
				.dessert(menu.getDessert())
				.menuDate(menu.getMenuDate())
				.empNo(menu.getEmployees().getEmpNo())
				.build();
		return dto;
	}
	
	public PageResponseDTO<MenuDTO> getMenuList(PageRequestDTO pageRequestDTO);
	
	public MenuDTO getOne(long menuNo);
	
	public void removeMenu(long menuNo);
	
	public void modMenu(MenuDTO menuDTO);
	
}
