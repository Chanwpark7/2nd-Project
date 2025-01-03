package com.fullstack.springboot.mail.dto;

import java.util.List;

import com.fullstack.springboot.dto.EmployeesDTO;

import lombok.Data;

@Data
public class CompanyMailReceivedDTO {
	List<EmployeesDTO> empDTO;
}
