package com.fullstack.springboot.dto;

import java.time.LocalDate;

import com.fullstack.springboot.entity.Menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
	
	long menuNo;
	LocalDate menuDate;
	String mainMenu;
	String firSideDish;
	String secSideDish;
	String thirdSideDish;
	String dessert;
	long empNo;
	
	public MenuDTO(Menu m) {
		this.menuNo = m.getMenuNo();
		this.menuDate = m.getMenuDate();
		this.mainMenu = m.getMainMenu();
		this.firSideDish = m.getFirSideDish();
		this.secSideDish = m.getSecSideDish();
		this.thirdSideDish = m.getThirdSideDish();
		this.dessert = m.getDessert();
		this.empNo = m.getEmployees().getEmpNo();
	}
	
}
