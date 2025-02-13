package com.fullstack.springboot.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyMailListRequestDTO {
	  @Builder.Default
	  private int page = 1;

	  @Builder.Default
	  private int size = 50;
	  
	  @Builder.Default
	  private String cat = "def";
}
