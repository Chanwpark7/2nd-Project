package com.fullstack.springboot.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public abstract class BaseEntity {
	
	@LastModifiedDate
	@Column(name= "regdate", updatable = true)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name= "moddate", updatable = true)
	private LocalDateTime modDate;

}
