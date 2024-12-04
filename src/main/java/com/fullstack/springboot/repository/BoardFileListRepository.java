package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.BoardFileList;

public interface BoardFileListRepository extends JpaRepository<BoardFileList, String> {

}
