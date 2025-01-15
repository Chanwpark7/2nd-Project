package com.fullstack.springboot.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fullstack.springboot.entity.DeptSchedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeptScheduleDTO {
    private long deptSchNo;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String scheduleText;
    private long deptNo;
    private String deptName;
    private long empNo;
    
    public DeptScheduleDTO(DeptSchedule ds) {
        this.deptSchNo = ds.getDeptSchNo();
        this.startDate = ds.getStartDate();
        this.endDate = ds.getEndDate();
        this.scheduleText = ds.getScheduleText();
        this.deptNo = ds.getDeptInfo().getDeptNo();
        this.deptName = ds.getDeptInfo().getDeptName(); // deptInfo entity 에 있는 deptName;
        this.empNo = ds.getEmployees().getEmpNo();  // employees entity 에 있는 empNo
    }
}



