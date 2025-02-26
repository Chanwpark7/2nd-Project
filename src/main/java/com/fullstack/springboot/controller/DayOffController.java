package com.fullstack.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.DayOffDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.DayOff;
import com.fullstack.springboot.service.commute.CommuteService;
import com.fullstack.springboot.service.dayoff.DayOffService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/dayoff")
public class DayOffController {

	private final DayOffService dayOffService;

	@GetMapping("/list")
	public PageResponseDTO<DayOffDTO> list(PageRequestDTO pageRequestDTO) {
		
		return dayOffService.getList(pageRequestDTO);
	}
	
	@GetMapping("/read/{dayOffNo}")
	public DayOffDTO read(@PathVariable(name = "dayOffNo") Long dayOffNo) {
		return dayOffService.getOne(dayOffNo);
	}
	
	@PutMapping("/{dayOffNo}")
	public void modify(@PathVariable(name = "dayOffNo") Long dayOffNo, @RequestBody DayOffDTO dayOffDTO) {
		
		dayOffService.modifyDayOff(dayOffDTO);
	}
	
	@DeleteMapping("/{dayOffNo}")
	public void delete(@PathVariable(name = "dayOffNo") Long dayOffNo) {
		dayOffService.removeDayOff(dayOffNo);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody DayOffDTO	dayOffDTO) {

		dayOffService.addDayOff(dayOffDTO);
	}
	
	@GetMapping("/todayList/{dayOffDate}")
	public List<DayOffDTO> getTodayDayOffList(@PathVariable("dayOffDate") LocalDate dayOffDate){
		System.out.println(dayOffDate);
		return dayOffService.getTodayDayOffList(dayOffDate);
	}
}
