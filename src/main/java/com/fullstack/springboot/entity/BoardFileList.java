package com.fullstack.springboot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BoardFileList extends BaseEntity {

	@Id
	private String fileUUID;
	
	private String boardFileOrginName;
	
	
}
