package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.DeptInfo;

public interface DeptInfoRepository extends JpaRepository<DeptInfo, Long> {

}
