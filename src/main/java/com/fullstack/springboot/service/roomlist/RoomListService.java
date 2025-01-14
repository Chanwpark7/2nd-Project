package com.fullstack.springboot.service.roomlist;

import java.util.List;

import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.dto.RoomListDTO;
import com.fullstack.springboot.entity.RoomList;

public interface RoomListService {

	public RoomListDTO createOrModifyRoom(RoomListDTO roomListDTO);
	
	public void deleteRoom(Long roomNo);
	
	public List<RoomListDTO> getRoomList();
	
	public RoomListDTO getOne(Long roomNo);
	
	public PageResponseDTO<BookingDTO> getBookingListPageByRoomNo(PageRequestDTO pageRequestDTO, Long roomNo);
	
	default RoomList dtoToEntity(RoomListDTO roomListDTO) {
		RoomList roomList = RoomList.builder()
				.roomNo(roomListDTO.getRoomNo())
				.roomName(roomListDTO.getRoomName())
				.location(roomListDTO.getLocation())
				.build();
		
		return roomList;
	}
	
	default RoomListDTO entityToDto(RoomList roomList) {
		RoomListDTO roomListDTO = RoomListDTO.builder()
				.roomNo(roomList.getRoomNo())
				.roomName(roomList.getRoomName())
				.location(roomList.getLocation())
				.build();
		
		return roomListDTO;
	}
}
