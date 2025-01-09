package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.dto.RoomListDTO;
import com.fullstack.springboot.entity.RoomList;

public interface RoomListRepository extends JpaRepository<RoomList, Long> {

}
