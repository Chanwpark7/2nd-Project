package com.fullstack.springboot.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
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
public class CompanyMailReceived {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long receivedId;
	
	@ManyToOne
	CompanyMail mail;
	@ManyToOne
	Employees employee;
}
