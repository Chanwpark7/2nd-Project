package com.fullstack.springboot.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long menuNo;
	
	LocalDate menuDate;
	
	String mainMenu;
	
	String firSideDish;
	
	String secSideDish;
	
	String thirdSideDish;
	
	String dessert;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employees employees;
	
	public void changeMainMenu(String mainMenu) {
		this.mainMenu = mainMenu;
	}
	
	public void changeFirSideDish(String firSideDish) {
		this.firSideDish = firSideDish;
	}
	
	public void changeSecSideDish(String secSideDish) {
		this.secSideDish = secSideDish;
	}
	
	public void changeThirdSideDish(String thirdSideDish) {
		this.thirdSideDish = thirdSideDish;
	}
	
	public void changeDessert(String dessert) {
		this.dessert = dessert;
	}
	
	public void changeMenuDate(LocalDate menuDate) {
		this.menuDate = menuDate;
	}
}
