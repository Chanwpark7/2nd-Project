package com.fullstack.springboot.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.MenuDTO;
import com.fullstack.springboot.entity.Menu;

public interface MenuRepositoy extends JpaRepository<Menu, Long> {

	@Query("select new com.fullstack.springboot.dto.MenuDTO(m) from Menu m where m.menuDate =:menuDate")
	MenuDTO getTodayMenu(@Param("menuDate")LocalDate menuDate);
	
	@Query("select new com.fullstack.springboot.dto.MenuDTO(m) from Menu m order by m.menuDate desc")
	Page<MenuDTO> getMenuList(Pageable pageable);
	
	@Query("select new com.fullstack.springboot.dto.MenuDTO(m) from Menu m where m.menuNo =:menuNo")
	Menu getOneMenu(@Param("menuNo") long menuNo);
	
	
}
