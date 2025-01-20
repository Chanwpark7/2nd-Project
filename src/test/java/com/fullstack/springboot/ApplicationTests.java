package com.fullstack.springboot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.AnnualLeaveDTO;
import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.dto.CommuteDTO;
import com.fullstack.springboot.dto.DayOffDTO;
import com.fullstack.springboot.dto.DeptInfoDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.JobDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.Booking;
import com.fullstack.springboot.entity.DeptInfo;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Job;
import com.fullstack.springboot.entity.RoomList;
import com.fullstack.springboot.entity.SalaryChart;
import com.fullstack.springboot.repository.BookingRepository;
import com.fullstack.springboot.repository.CommuteRepository;
import com.fullstack.springboot.repository.DeptInfoRepository;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.repository.JobRepository;
import com.fullstack.springboot.repository.RoomListRepository;
import com.fullstack.springboot.repository.SalaryChartRepository;
import com.fullstack.springboot.service.EmployeesService;
import com.fullstack.springboot.service.annualleave.AnnualleaveService;
import com.fullstack.springboot.service.booking.BookingService;
import com.fullstack.springboot.service.commute.CommuteService;
import com.fullstack.springboot.service.dayoff.DayOffService;
import com.fullstack.springboot.service.deptinfo.DeptInfoService;
import com.fullstack.springboot.service.job.JobService;
import com.fullstack.springboot.service.roomlist.RoomListService;

import jakarta.persistence.Version;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class ApplicationTests {

	@Autowired
	private EmployeesRepository employeesRepository;

	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private SalaryChartRepository salaryChartRepository;
	
	@Autowired
	private RoomListRepository roomListRepository;

	@Autowired
	private RoomListService roomListService;
	
	@Autowired
	private DeptInfoRepository deptInfoRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private CommuteService commuteService;
	
	@Autowired
	private CommuteRepository commuteRepository;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private DeptInfoService deptInfoService;
	
	@Autowired
	private EmployeesService employeesService;
	
	@Autowired
	private AnnualleaveService annualleaveService;
	
	@Autowired
	private DayOffService dayOffService;
	
	
}
