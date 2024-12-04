package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.RoomList;

public interface RoomListRepository extends JpaRepository<RoomList, Long> {

}
