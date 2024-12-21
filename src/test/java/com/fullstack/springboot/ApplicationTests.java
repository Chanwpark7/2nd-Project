package com.fullstack.springboot;

import java.time.LocalDateTime;
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

import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.dto.CommuteDTO;
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
import com.fullstack.springboot.service.booking.BookingService;
import com.fullstack.springboot.service.commute.CommuteService;
import com.fullstack.springboot.service.deptinfo.DeptInfoService;
import com.fullstack.springboot.service.job.JobService;

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
	
	@Test
//	 void insertDummies() {
//	      Job job = Job.builder()
//	            .jobNo(100L)
//	            .jobTitle("DIRECTOR")
//	            .build();
//
//	      jobRepository.save(job);
//	      
//	      job = Job.builder()
//	            .jobNo(200L)
//	            .jobTitle("MANAGER")
//	            .build();
//
//	      jobRepository.save(job);
//	      
//	      job = Job.builder()
//	            .jobNo(300L)
//	            .jobTitle("SENIOR")
//	            .build();
//
//	      jobRepository.save(job);
//	      
//	      job = Job.builder()
//	            .jobNo(400L)
//	            .jobTitle("EMPLOYEE")
//	            .build();
//
//	      jobRepository.save(job);
//	      
//	      job = Job.builder()
//	      .jobNo(500L)
//	      .jobTitle("INTERN")
//	      .build();
//	      
//	      jobRepository.save(job);
//		
//		SalaryChart salaryChart = SalaryChart.builder()
//				.saleryNo(100L)
//				.job(Job.builder()
//						.jobNo(100L)
//						.build())
//				.minSalary(6500L)
//				.maxSalary(7500L)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//		salaryChart = SalaryChart.builder()
//				.saleryNo(200L)
//				.job((Job)jobRepository.getJobById(200L))
//				.minSalary(5500L)
//				.maxSalary(6500L)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//		salaryChart = SalaryChart.builder()
//				.saleryNo(300L)
//				.job((Job)jobRepository.getJobById(300L))
//				.minSalary(4500L)
//				.maxSalary(5500L)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//		salaryChart = SalaryChart.builder()
//				.saleryNo(400L)
//				.job((Job)jobRepository.getJobById(400L))
//				.minSalary(3500L)
//				.maxSalary(4500L)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//		salaryChart = SalaryChart.builder()
//				.saleryNo(500L)
//				.job((Job)jobRepository.getJobById(500L))
//				.minSalary(2500L)
//				.maxSalary(3500L)
//				.build();
//
//		salaryChartRepository.save(salaryChart);
//		
//	}
	
//	void insertDummies() {
//		DeptInfo deptInfo = DeptInfo.builder()
//				.deptNo(100L)
//				.deptName("GA")
//				.deptAddress("GA Building")
//				.phoneNo("02-100-100")
//				.build();
//		
//		deptInfoRepository.save(deptInfo);
//		
//		deptInfo = DeptInfo.builder()
//				.deptNo(200L)
//				.deptName("HR")
//				.deptAddress("HR Building")
//				.phoneNo("02-100-200")
//				.build();
//		
//		deptInfoRepository.save(deptInfo);
//		
//		deptInfo = DeptInfo.builder()
//				.deptNo(300L)
//				.deptName("ACC")
//				.deptAddress("ACC Building")
//				.phoneNo("02-100-300")
//				.build();
//		
//		deptInfoRepository.save(deptInfo);
//	}

//	void insertDummies() {
//		RoomList roomList = RoomList.builder()
//				.roomNo(101L)
//				.roomName("회의실1")
//				.location("901호")
//				.build();
//		
//		roomListRepository.save(roomList);
//		
//		roomList = RoomList.builder()
//				.roomNo(102L)
//				.roomName("회의실2")
//				.location("902호")
//				.build();
//		
//		roomListRepository.save(roomList);
//		
//		roomList = RoomList.builder()
//				.roomNo(103L)
//				.roomName("회의실3")
//				.location("903호")
//				.build();
//		
//		roomListRepository.save(roomList);
//		
//		roomList = RoomList.builder()
//				.roomNo(201L)
//				.roomName("변기 901")
//				.location("9층 남자 1번")
//				.build();
//		
//		roomListRepository.save(roomList);
//		
//		roomList = RoomList.builder()
//				.roomNo(211L)
//				.roomName("변기 911")
//				.location("9층 여자 1번")
//				.build();
//		
//		roomListRepository.save(roomList);
//	}
	
//	void insertDummies() {
//		IntStream.rangeClosed(1, 100).forEach(i -> {
//			Employees employees = Employees.builder().build();
//			if(i<20) {
//				System.out.println("성공!");
//				long salary = (long)(Math.random()*1000)+6500;
//				if(i<5) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(100L).build())
//							.deptInfo(DeptInfo.builder().deptNo(100L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("seoul")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.build();
//				}else if(i<10) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(100L).build())
//							.deptInfo(DeptInfo.builder().deptNo(200L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("seoul")
//							.phoneNum("010-1111-1111")
//							.gender("f")
//							.citizenId("0000000000000")
//							.build();
//				}else {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(100L).build())
//							.deptInfo(DeptInfo.builder().deptNo(300L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("seoul")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.build();
//				}
//				employeesRepository.save(employees);
//			}else if(i<40) {
//				long salary = (long)(Math.random()*1000)+5500;
//				if(i<25) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(200L).build())
//							.deptInfo(DeptInfo.builder().deptNo(100L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("daejeon")
//							.phoneNum("010-1111-1111")
//							.gender("f")
//							.citizenId("0000000000000")
//							.build();
//				}else if(i<30) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(200L).build())
//							.deptInfo(DeptInfo.builder().deptNo(200L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("daejeon")
//							.phoneNum("010-1111-1111")
//							.gender("f")
//							.citizenId("0000000000000")
//							.build();
//				}else {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(200L).build())
//							.deptInfo(DeptInfo.builder().deptNo(300L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("daejeon")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.build();
//				}
//				employeesRepository.save(employees);
//			}else if(i<60) {
//				long salary = (long)(Math.random()*1000)+4500;
//				if(i<45) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(300L).build())
//							.deptInfo(DeptInfo.builder().deptNo(100L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("daegu")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.build();
//				}else if(i<50) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(300L).build())
//							.deptInfo(DeptInfo.builder().deptNo(200L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("daegu")
//							.phoneNum("010-1111-1111")
//							.gender("f")
//							.citizenId("0000000000000")
//							.build();
//				}else {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(300L).build())
//							.deptInfo(DeptInfo.builder().deptNo(300L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("daegu")
//							.phoneNum("010-1111-1111")
//							.gender("f")
//							.citizenId("0000000000000")
//							.build();
//				}
//				employeesRepository.save(employees);
//			}else if(i<80) {
//				long salary = (long)(Math.random()*1000)+3500;
//				if(i<65) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(400L).build())
//							.deptInfo(DeptInfo.builder().deptNo(100L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("busan")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.build();
//				}else if(i<70) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(400L).build())
//							.deptInfo(DeptInfo.builder().deptNo(200L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("busan")
//							.phoneNum("010-1111-1111")
//							.gender("f")
//							.citizenId("0000000000000")
//							.build();
//				}else {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(400L).build())
//							.deptInfo(DeptInfo.builder().deptNo(300L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("busan")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.build();
//				}
//				employeesRepository.save(employees);
//			}else{
//				long salary = (long)(Math.random()*1000)+2500;
//				if(i<85) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(500L).build())
//							.deptInfo(DeptInfo.builder().deptNo(100L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("ulsan")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.build();
//				}else if(i<90) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(500L).build())
//							.deptInfo(DeptInfo.builder().deptNo(200L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("ulsan")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.build();
//				}else {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(500L).build())
//							.deptInfo(DeptInfo.builder().deptNo(300L).build())
//							.birthday(LocalDateTime.of(2000, 1, 1, 0, 0, 0))
//							.address("ulsan")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.build();
//				}
//				employeesRepository.save(employees);
//			}
//		});
//	}
	
//	void insertDummiesBooking() {
//		IntStream.rangeClosed(1, 11).forEach(i -> {
//			BookingDTO bookingDTO = BookingDTO.builder()
//					.bookDate(LocalDateTime.now())
//					.start(LocalDateTime.now())
//					.end(LocalDateTime.now())
//					.RoomNo(201L)
//					.empNo(2L)
//					.build();
//			
//			bookingService.addBooking(bookingDTO);
//		});
//	}
	
//	void testGetList() {
//		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//				.size(10)
//				.page(1)
//				.build();
//		
//		Page<BookingDTO> result = bookingService.getWRBookingList(pageRequestDTO);
//
//		log.error(result.getPageable());
//		for(BookingDTO res : result) {
//			log.error(res);
//		}
//	}
	
//	void getList() {
//		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//				.size(10)
//				.page(1)
//				.build();
//		bookingService.getBookingList(pageRequestDTO);
//	}
	
//	void remove() {
//		bookingService.remove(50L);
//	}
	
//	void addCommute() {
//		CommuteDTO commuteDTO = CommuteDTO.builder()
//				.empNo(1L)
//				.build();
//		
//		commuteService.addCommute(commuteDTO);
//	}
	
//	void oimocheck() {
//		commuteService.checkOut(1L);
//	}
	
//	void getpage() {
//		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//				.size(10)
//				.page(1)
//				.build();
//		
//		Page<CommuteDTO> page = commuteService.getListCommute(1L, pageRequestDTO);
//		
//		for(CommuteDTO dto : page) {
//			log.error(dto);
//		}
//	};
	
//	void jobCrudTest() {
//		
//		JobDTO jobDTO = JobDTO.builder()
//				.jobNo(998L)
//				.jobTitle("ADMIN1")
//				.build();
//		
//		//jobService.createOrModifyJob(jobDTO);
//		
//		//jobService.deleteJob(998L);
//		
//		List<JobDTO> list = jobService.jobList();
//		for(JobDTO dto : list) {
//			log.error(dto);
//		}
//	}
	
//	void deptCRUDTest() {
//		DeptInfoDTO deptInfoDTO = DeptInfoDTO.builder()
//				.deptNo(999L)
//				.deptName("ADMIN11")
//				.deptAddress("ADMIN11")
//				.phoneNo("999-999")
//				.build();
//		
//		//deptInfoService.createOrModifyDept(deptInfoDTO);
//		
//		//deptInfoService.deleteDept(999L);
//		
//		List<DeptInfoDTO> list = deptInfoService.getDeptList();
//		for(DeptInfoDTO dto : list) {
//			log.error(dto);
//		}
//	}
	
	void employeesCRUDTest() {
		EmployeesDTO employeesDTO = EmployeesDTO.builder()
				.empNo(101L)
				.firstName("A")
				.lastName("DMIN")
				.hireDate(LocalDateTime.of(2000, 1, 1, 0, 0))
				.mailAddress("1@1")
				.salary(100L)
				.deptNo(100L)
				.jobNo(100L)
				.birthday(LocalDateTime.of(2000, 1, 1, 1, 1))
				.address("ADMIN")
				.phoneNum("01011111")
				.gender("m")
				.citizenId("1111111111111")
				.build();
		
		//employeesService.deleteEmployees(101L);
		
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
				.page(1)
				.size(10)
				.build();
		
		Page<EmployeesDTO> page = employeesService.getEmployeesListPage(pageRequestDTO);
		
		for(EmployeesDTO dto : page) {
			log.error(dto);
		}
	}
}
