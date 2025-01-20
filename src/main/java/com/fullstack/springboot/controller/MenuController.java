package com.fullstack.springboot.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fullstack.springboot.dto.MenuDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.service.MenuService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
@RequestMapping("/menu")
public class MenuController {

	private final MenuService menuService;
	
	@GetMapping("/read/{menuDate}")
	public MenuDTO getTodayMenu(@PathVariable("menuDate")LocalDate menuDate) {
		//System.out.println("여기까지 옴");
		return menuService.getTodayMenu(menuDate);
	}
	
	@PostMapping("/add")
	public void addMenu(@RequestBody MenuDTO menuDTO) {
		System.out.println("여기옴!");
		
		menuService.addMenu(menuDTO);
	}
	
	@GetMapping("/list")
	public PageResponseDTO<MenuDTO> list(PageRequestDTO pageRequestDTO){
		if(pageRequestDTO.getPage() < 0) {
			pageRequestDTO.setPage(0);
		}
		return menuService.getMenuList(pageRequestDTO);
	}
	
	@GetMapping("/readMenu/{menuNo}")
	public MenuDTO read(@PathVariable("menuNo")long menuNo) {
		System.out.println("????");
		return menuService.getOne(menuNo);
	}
	
	@DeleteMapping("/{menuNo}")
	public void delete(@PathVariable("menuNo")long menuNo) {
		menuService.removeMenu(menuNo);
	}
	
	@PutMapping("/{menuNo}")
	public String modify(@PathVariable("menuNo")long menuNO, @RequestBody MenuDTO menuDTO) {
		menuService.modMenu(menuDTO);
		log.error(menuDTO);
		return "success";
	}
}
