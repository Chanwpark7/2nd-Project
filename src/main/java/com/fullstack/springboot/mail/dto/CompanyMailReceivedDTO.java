package com.fullstack.springboot.mail.dto;

import java.util.List;

import com.fullstack.springboot.dto.EmployeesDTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CompanyMailReceivedDTO {
	Long mailNo;
	List<EmployeesDTO> empDTO;
}
