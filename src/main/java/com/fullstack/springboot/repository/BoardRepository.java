package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}