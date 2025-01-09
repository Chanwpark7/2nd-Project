package com.fullstack.springboot.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name="Board")
public class Board extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long boardNo;
	
	@Column(length = 100, nullable = false)
	private String title;
	
	@Column(length = 100, nullable = false)
	private String contents;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<BoardFileList> boardFileList;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employees employees;
	
	private String category;
	
	public void changeTitle(String title) {
		this.title = title;
	}
	public void changeContent(String contents) {
		this.contents = contents;
	}
}
