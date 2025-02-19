package com.fullstack.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.dto.RoomListDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.service.roomlist.RoomListService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/room")
public class RoomListController {

	private final RoomListService roomListService;
	
	@GetMapping("/list")
	public List<RoomListDTO> list() {
		
		return roomListService.getRoomList();
	}
	
	@GetMapping("/read/{roomNo}")
	public RoomListDTO getOne(@PathVariable(name = "roomNo") Long roomNo) {
		return roomListService.getOne(roomNo);
	}
	
	@GetMapping("/list/{roomNo}")
	public PageResponseDTO<BookingDTO> getEmpList(PageRequestDTO pageRequestDTO, @PathVariable(name = "roomNo") Long roomNo) {
		
		return roomListService.getBookingListPageByRoomNo(pageRequestDTO, roomNo);
	}
	
	@PutMapping("/{roomNo}")
	public String modify(@PathVariable(name = "roomNo") Long roomNo, @RequestBody RoomListDTO roomListDTO) {
		roomListService.ModifyRoom(roomListDTO);
		return "success";
	}
	
	@DeleteMapping("/{roomNo}")
	public void delete(@PathVariable(name = "roomNo") Long roomNo) {
		roomListService.deleteRoom(roomNo);
	}
	
	@PostMapping("/add")
	public void postMethodName(@RequestBody RoomListDTO roomListDTO) {
		roomListService.createRoom(roomListDTO);
	}
}
