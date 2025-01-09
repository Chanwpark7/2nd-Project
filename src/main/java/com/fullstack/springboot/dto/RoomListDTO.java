package com.fullstack.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class RoomListDTO {

	private long roomNo;
	
	private String roomName;
	
	private String location;
	
	public RoomListDTO(long roomNo, String roomName,  String location) {
			this.roomNo = roomNo;
			this.roomName = roomName;
			this.location = location;
	}
}
