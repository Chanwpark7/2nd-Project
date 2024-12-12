package com.fullstack.springboot.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompanyMail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mailNo;
	
	private LocalDateTime sendDate;
	
	private String contents;
	
	private String title;
	
	private String mailFileOriginName;
	
	private String mailFileUUID;
	
	@OneToMany(fetch = FetchType.LAZY)
	@Builder.Default
	private List<Employees> employees = new ArrayList<Employees>();
	
	private String mailCategory;

}
