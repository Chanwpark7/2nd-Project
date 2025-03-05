package com.fullstack.springboot;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.entity.AnnualLeave;
import com.fullstack.springboot.entity.Board;
import com.fullstack.springboot.dto.AnnualLeaveDTO;
import com.fullstack.springboot.dto.BookingDTO;
import com.fullstack.springboot.dto.CommuteDTO;
import com.fullstack.springboot.dto.CompanyChatDTO;
import com.fullstack.springboot.dto.DayOffDTO;
import com.fullstack.springboot.dto.DeptInfoDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.EmployeesImageDTO;
import com.fullstack.springboot.dto.JobDTO;
import com.fullstack.springboot.dto.MenuDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.Booking;
import com.fullstack.springboot.entity.DayOff;
import com.fullstack.springboot.entity.DeptInfo;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.Job;
import com.fullstack.springboot.entity.Menu;
import com.fullstack.springboot.entity.Report;
import com.fullstack.springboot.entity.ReportHistory;
import com.fullstack.springboot.entity.RoomList;
import com.fullstack.springboot.repository.AnnualleaveRepository;
import com.fullstack.springboot.repository.BoardRepository;
import com.fullstack.springboot.repository.BookingRepository;
import com.fullstack.springboot.repository.CommuteRepository;
import com.fullstack.springboot.repository.CompanyChatFilesRepository;
import com.fullstack.springboot.repository.CompanyChatRepository;
import com.fullstack.springboot.repository.DayOffRepository;
import com.fullstack.springboot.repository.DeptInfoRepository;
import com.fullstack.springboot.repository.EmployeesImageRepository;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.repository.JobRepository;
import com.fullstack.springboot.repository.MenuRepositoy;

import com.fullstack.springboot.repository.ReportHistoryRepository;
import com.fullstack.springboot.repository.ReportRepository;
import com.fullstack.springboot.repository.RoomListRepository;
import com.fullstack.springboot.service.BoardService;
import com.fullstack.springboot.service.CompanyChatService;
import com.fullstack.springboot.service.EmployeesImageService;
import com.fullstack.springboot.service.EmployeesService;
import com.fullstack.springboot.service.MenuService;
import com.fullstack.springboot.service.annualleave.AnnualleaveService;
import com.fullstack.springboot.service.booking.BookingService;
import com.fullstack.springboot.service.commute.CommuteService;
import com.fullstack.springboot.service.dayoff.DayOffService;
import com.fullstack.springboot.service.deptinfo.DeptInfoService;
import com.fullstack.springboot.service.job.JobService;
import com.fullstack.springboot.service.report.ReportService;
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
	private ReportRepository reportRepository;
	
	@Autowired
	private RoomListRepository roomListRepository;

	@Autowired
	private RoomListService roomListService;
	
	@Autowired
	private DeptInfoRepository deptInfoRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	


	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ReportHistoryRepository reportHistoryRepository;
	
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

	@Autowired
	private PasswordEncoder pwencoder;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
  
	@Autowired
	private BoardService boardService;
	

	@Autowired
	private DayOffRepository dayOffRepository;
	
	@Autowired
	private AnnualleaveRepository annualleaveRepository;
	
	@Autowired
	private MenuRepositoy menuRepositoy;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private CompanyChatRepository companyChatRepository;
	
	@Autowired
	private CompanyChatService companyChatService;
	

	private CompanyChatFilesRepository companyChatFilesRepository;

	
	@Autowired
	private ReportService reportService;

//	@Test
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
	
//	@Test
//	void test() {
//		long salary = (long)(Math.random()*1000)+6500;
//		Employees employees = Employees.builder().firstName("관").lastName("리자").mailAddress("aaaa@ddd.com")
//				.salary(salary).job(Job.builder().jobNo(999L).build()).deptInfo(DeptInfo.builder().deptNo(999L).build())
//				.birthday(LocalDate.of(2000, 1, 1)).address("seoul").phoneNum("010-1234-5678").gender("m").citizenId("0000000000000")
//				.password(passwordEncoder.encode("1111")).build();
//			employeesRepository.save(employees);
//	}
	
//	@Transactional
//	@Test
//	void tes() {
//		System.out.println(employeesImageRepository.getOneEmpImg(1L));
//	}
	
//	@Test
//	void tes() {
//		EmployeesImageDTO dto = EmployeesImageDTO.builder()
//				//.empImgNo(null)
//				.empNo(2L)				
//				.url("D:\\kdt_study\\이미지\\이미지2.jpg")
//				.build();
//		employeesImageService.register(dto);
//		System.out.println("성공");
//	}
////	
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
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("seoul")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.password("1111")
//							.build();
//				}else if(i<10) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(100L).build())
//							.deptInfo(DeptInfo.builder().deptNo(200L).build())
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("seoul")
//							.phoneNum("010-1111-1111")
//							.gender("f")
//							.citizenId("0000000000000")
//							.password("1111")
//							.build();
//				}else {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(100L).build())
//							.deptInfo(DeptInfo.builder().deptNo(300L).build())
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("seoul")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.password("1111")
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
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("daejeon")
//							.phoneNum("010-1111-1111")
//							.gender("f")
//							.citizenId("0000000000000")
//							.password("1111")
//							.build();
//				}else if(i<30) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(200L).build())
//							.deptInfo(DeptInfo.builder().deptNo(200L).build())
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("daejeon")
//							.phoneNum("010-1111-1111")
//							.password("1111")
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
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("daejeon")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.password("1111")
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
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("daegu")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.password("1111")
//							.build();
//				}else if(i<50) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(300L).build())
//							.deptInfo(DeptInfo.builder().deptNo(200L).build())
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("daegu")
//							.phoneNum("010-1111-1111")
//							.gender("f")
//							.password("1111")
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
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("daegu")
//							.phoneNum("010-1111-1111")
//							.gender("f")
//							.citizenId("0000000000000")
//							.password("1111")
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
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("busan")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.password("1111")
//							.build();
//				}else if(i<70) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(400L).build())
//							.deptInfo(DeptInfo.builder().deptNo(200L).build())
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("busan")
//							.phoneNum("010-1111-1111")
//							.gender("f")
//							.password("1111")
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
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("busan")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.password("1111")
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
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("ulsan")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.password("1111")
//							.build();
//				}else if(i<90) {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(500L).build())
//							.deptInfo(DeptInfo.builder().deptNo(200L).build())
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("ulsan")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.password("1111")
//							.build();
//				}else {
//					employees = Employees.builder()
//							.firstName("f"+i)
//							.lastName("l"+i)
//							.mailAddress("f"+i+"l"+i+"@ddt.co")
//							.salary(salary)
//							.job(Job.builder().jobNo(500L).build())
//							.deptInfo(DeptInfo.builder().deptNo(300L).build())
//							.birthday(LocalDate.of(2000, 1, 1))
//							.address("ulsan")
//							.phoneNum("010-1111-1111")
//							.gender("m")
//							.citizenId("0000000000000")
//							.password(pass)
//							.build();
//				}
//				employeesRepository.save(employees);
//			}
//		});
//	}
	
//	void insertDummiesBooking() {
//		//IntStream.rangeClosed(1, 11).forEach(i -> {
//			BookingDTO bookingDTO = BookingDTO.builder()
//					.bookDate(LocalDateTime.now())
//					.start(LocalDateTime.now())
//					.end(LocalDateTime.now())
//					.RoomNo(201L)
//					.empNo(2L)
//					.build();
//			
//			bookingService.modify(1L, bookingDTO);
//		//});
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
	
//	void employeesCRUDTest() {
////		EmployeesDTO employeesDTO = EmployeesDTO.builder()
////				.empNo(100L)
////				.firstName("A")
////				.lastName("DMIN")
////				.hireDate(LocalDateTime.of(2000, 1, 1, 0, 0))
////				.mailAddress("1@1")
////				.salary(100L)
////				//.deptNo(100L)
////				//.jobNo(100L)
////				.birthday(LocalDateTime.of(2000, 1, 1, 1, 1))
////				.address("ADMIN")
////				.phoneNum("01011111")
////				.gender("m")
////				.citizenId("1111111111111")
////				.build();
//		
//		//employeesService.deleteEmployees(101L);
//		
//		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//				.page(2)
//				.size(10)
//				.build();
//		
//		PageResponseDTO<BookingDTO> page = roomListService.getBookingListPageByRoomNo(pageRequestDTO, 101L);
//		
//		for(BookingDTO emp : page.getDtoList()) {
//			log.error(emp);
//		}
//		
//		log.error(page.getPageRequestDTO().getPageable(Sort.by("empNo")));
//	}

//	void annualLeaveTest() {
//		IntStream.rangeClosed(1, 100).forEach(i -> {
//			//annualleaveService.deleteAnnualleave((long)i);
//			
//			annualleaveService.setAnnualleave((long) i);
//		});
//		
//		AnnualLeaveDTO annualLeaveDTO = AnnualLeaveDTO.builder()
//				.annualId(101L)
//				.antecedent(1)
//				.empNo(1L)
//				.hours(0L)
//				.build();
//		
//		//log.error(annualleaveService.getOne(annualLeaveDTO));
//		
//		//annualleaveService.deleteAnnualleave(annualLeaveDTO.getEmpNo());
//		
//		//annualleaveService.setAnnualleave(1L);
//		
//		//annualleaveService.modifyAnnualleave(annualLeaveDTO);
//	}
	
//	void dayOffTest() {
//		DayOffDTO dayOffDTO = DayOffDTO.builder()
//				.empNo(1L)
//				.offHours(2L)
//				.dayOffDate(LocalDateTime.now())
//				.build();
//				
//		//dayOffService.addDayOff(dayOffDTO);
//		
//		//dayOffService.removeDayOff(DayOffDTO.builder().empNo(1L).dayOffDate(LocalDateTime.now()).build());
//		
//		dayOffService.modifyDayOff(dayOffDTO);
//	}
	
//	void roomListRepTest() {
//		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//				.page(2)
//				.size(10)
//				.build();
//		
//		PageResponseDTO<BookingDTO> page = roomListService.getBookingListPageByRoomNo(pageRequestDTO, 101L);
//		
//		for(BookingDTO dto : page.getDtoList()) {
//			log.error(dto);
//		}
//		
//		log.error(page.getPageRequestDTO().getPageable(Sort.by("empNo")));
//	}

	
//	void addBookingService() {
//		BookingDTO bookingDTO = BookingDTO.builder()
//				.bookDate("2025-01-11")
//				.empNo(12L)
//				.start("11:11")
//				.end("21:11")
//				.roomNo(101L)
//				.build();
//		
//		bookingService.addBooking(bookingDTO);
//	}
	
//	void getOneTest() {
//		log.error(Long.parseLong(employeesRepository.getMaxEmpNo().toString()));
//	}
	
//	void insert() {
//		Job job = Job.builder()
//				.jobNo(999L)
//				.jobTitle("admin")
//				.build();
//		
//		jobRepository.save(job);
//		
//		DeptInfo deptInfo = DeptInfo.builder()
//				.deptNo(999L)
//				.deptName("admin")
//				.deptAddress("admin")
//				.build();
//		
//		deptInfoRepository.save(deptInfo);
//		
//		Employees employees = Employees.builder()
//				.firstName("admin")
//				.lastName("admin")
//				.mailAddress("admin")
//				.salary(1)
//				.job(Job.builder().jobNo(999L).build())
//				.deptInfo(DeptInfo.builder().deptNo(999L).build())
//				.birthday(LocalDate.of(2000, 1, 1))
//				.address("admin")
//				.phoneNum("010-1111-1111")
//				.gender("m")
//				.citizenId("0000000000000")
//				.password(pwencoder.encode("1234"))
//				.build();
//		
//		employeesRepository.save(employees);
//	}
	
//	void insertDummies() {
//		IntStream.rangeClosed(1, 20).forEach(value -> {
//			Board board = Board.builder()
//					.title(value+"")
//					.contents(value+"")
//					.employees(Employees.builder().empNo(2L).build())
//					.category("긴급")
//					.build();
//			boardRepository.save(board);
//		});
//		
//	}
	
//		void insertReply() {
//			//replyer 는 반드시 member email 중 하나여야 하고, 랜덤하게 생성해서 하나의 게시글에 하나이상의 댓글을 구성하도록 합니다.
//
//				IntStream.rangeClosed(1, 100).forEach(i->{
//				long boardNo = (long)(Math.random() * 20) + 1;
//				Board board = Board.builder().boardNo(boardNo).build();
//				
//				Reply reply = Reply.builder()
//						.text("댓글..." + i)
//						.board(board)
//						.replyer("f2l2@ddt.co")
//						.build();
//				replyRepository.save(reply);
//			});
//		}
	
//	void test() {
//		log.error(boardService.getRead(2L));
//	}
//  
//	void test() {
//		CommuteDTO dto = commuteRepository.todayCheckTime(205L);
//		log.error(dto);
//	}

//	void ins() {
//		Employees employees = Employees.builder().empNo(205L).build();
//		AnnualLeave annualLeave = AnnualLeave.builder()
//				.employees(employees)
//				.antecedent(1)
//				.hours(0)
//				.build();
//		annualleaveRepository.save(annualLeave);
//	}
	
//	void tes() {
//		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//				.page(1)
//				.size(10)
//				.build();
//		Employees employees = Employees.builder().empNo(110L).build();
//		
//		log.error(reportService.getSentList(110L, pageRequestDTO));
//	}
	
//	void tes() {
//		DayOff dayOff = DayOff.builder().dayOffDate(LocalDate.now()).offHours(2L).employees(Employees.builder().empNo(205L).build()).build();
//		System.out.println("성공");
//		dayOffRepository.save(dayOff);
//	}
	
	
//	void tes() {
//		Employees employees = Employees.builder().empNo(205L).build();
//		Menu menu = Menu.builder().dessert("하겐다즈")
//				.mainMenu("스파게티")
//				.firSideDish("양송이 스프")
//				.secSideDish("마늘바게트")
//				.thirdSideDish("피클")
//				.employees(employees)
//				.menuDate(LocalDate.now()).build();
//		
//		menuRepositoy.save(menu);
//	}
	
//	@Transactional
//	void tes() {
//		MenuDTO menuDTO = MenuDTO.builder().dessert("하겐다즈").mainMenu("떡볶이").firSideDish("단무지").secSideDish("치즈스틱")
//				.thirdSideDish("야채튀김").menuDate(LocalDate.now()).empNo(205L).build();
//		menuService.addMenu(menuDTO);
//	}
	
//	void tess() {
//		System.out.println(menuService.getOne(1L));
//	}
	
//	void tes() {
//		
//		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
//				.page(1)
//				.size(10)
//				.build();
//		
//		PageResponseDTO<EmployeesDTO> dto = employeesService.getBirthEmp(pageRequestDTO);
//		for(EmployeesDTO res : dto.getDtoList()) {
//			System.out.println(dto);
//		}
//	}
	
	
//	void ttt() {
//		List<MenuDTO> dto = menuRepositoy.getMenuList();
//		for(MenuDTO res : dto) {
//			System.out.println(res);
//		}
//	}
//	


//	void tes() {
//		System.out.println( employeesService.getDDay(205L));
//		log.error(employeesService.getDDay(205L));	
//		}
//
//	void tes() {
//		LocalDate now = LocalDate.now();
//		System.out.println(now);
//		MenuDTO dto = menuRepositoy.getTodayMenu(now);
//		System.out.println("성공?");
//		System.out.println(dto);
//	}	

//	@Test
//	void test() {
//		log.error(bookingService.getBookingListAtDate("2025-02-12",111L));
//	}
	
//	@Test
//	void tes() {
//		Employees employees = Employees.builder()
//				.firstName("일")
//				.lastName("이삼")
//				.mailAddress("aa@abc.com")
//				.salary(1)
//				.job(Job.builder().jobNo(999L).build())
//				.deptInfo(DeptInfo.builder().deptNo(999L).build())
//				.birthday(LocalDate.of(2000, 1, 1))
//				.address("서울")
//				.phoneNum("010-1111-1111")
//				.gender("m")
//				.citizenId("0000000000000")
//				.password(pwencoder.encode("1111"))
//				.build();
//		
//		employeesRepository.save(employees);
//	}
	
	
//	void tes() {
//		String attachOrginName = "seoul.jpg";
//		System.out.println(companyChatFilesRepository.getImg(attachOrginName));
//
//	}
	
//	@Test
//	void tes() {
//		log.warn("!!!!1");
//		LocalDate now = LocalDate.of(2025, 02, 20);
//		System.out.println("nnn" + now);
//		List<DayOff> day = dayOffRepository.getTodayDayOffList(now);
//		for(DayOff res : day) {
//			
//			System.out.println("!!!" + res);
//		}
//	}
	
	@Test
	@Transactional
	void te() {
		employeesService.allListWithDeptAndJob().stream().forEach(t -> {
			log.error(t);
		});
	}
}

