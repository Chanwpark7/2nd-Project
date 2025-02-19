package com.fullstack.springboot.service.roomlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.dto.RoomListDTO;
import com.fullstack.springboot.entity.RoomList;
import com.fullstack.springboot.repository.BookingRepository;
import com.fullstack.springboot.repository.RoomListRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class RoomListServiceImpl implements RoomListService{
	
	@Autowired
	private final RoomListRepository roomListRepository;
	
	@Autowired
	private final BookingRepository bookingRepository;
	
	@Override
	public RoomListDTO createRoom(RoomListDTO roomListDTO) {
		roomListRepository.save(dtoToEntity(roomListDTO));
		
		return roomListDTO;
	}
	
	@Override
	public RoomListDTO ModifyRoom(RoomListDTO roomListDTO) {
		roomListRepository.save(dtoToEntityWithRoomNo(roomListDTO));
		
		return roomListDTO;
	}

	@Override
	public void deleteRoom(Long roomNo) {
		
		roomListRepository.delete(roomListRepository.findById(roomNo).get());
	}

	@Override
	public List<RoomListDTO> getRoomList() {

		List<RoomListDTO> list = roomListRepository.getRoomList();
		
		return list;
	}

	@Override
	public RoomListDTO getOne(Long roomNo) {

		RoomList roomList = roomListRepository.findById(roomNo).get();
		
		return entityToDto(roomList);
	}
	
	@Override
	public PageResponseDTO<BookingDTO> getBookingListPageByRoomNo(PageRequestDTO pageRequestDTO, Long roomNo){

		Pageable pageable = pageRequestDTO.getPageable(Sort.by("bookNo").descending());
		
		Page<BookingDTO> page = bookingRepository.getBookingListByRoomNo(pageable, roomNo);
		
		List<BookingDTO> dtoList = page.get().toList();
		
		long totalCount = page.getTotalElements();
		
		return PageResponseDTO.<BookingDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}
}
