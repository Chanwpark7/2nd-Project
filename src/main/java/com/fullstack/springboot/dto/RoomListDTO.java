package com.fullstack.springboot.dto;

import com.fullstack.springboot.entity.RoomList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomListDTO {
	
	private Long roomNo;
	
	private String roomName;
	
	private String location;
	
	public RoomListDTO(RoomList roomList) {
		this.roomNo = roomList.getRoomNo();
		this.roomName = roomList.getRoomName();
		this.location = roomList.getLocation();
	}

}
