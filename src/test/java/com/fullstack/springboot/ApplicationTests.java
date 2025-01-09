package com.fullstack.springboot;


import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.springboot.dto.CompanyChatDTO;
import com.fullstack.springboot.dto.CompanyChatMemberDTO;
import com.fullstack.springboot.dto.DeptScheduleDTO;
import com.fullstack.springboot.dto.EmpScheduleDTO;
import com.fullstack.springboot.dto.RoomListDTO;
import com.fullstack.springboot.entity.CompanyChat;
import com.fullstack.springboot.entity.CompanyChatMember;
import com.fullstack.springboot.entity.DeptInfo;
import com.fullstack.springboot.entity.DeptSchedule;
import com.fullstack.springboot.entity.EmpSchedule;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.entity.RoomList;
import com.fullstack.springboot.repository.CompanyChatMemberRepository;
import com.fullstack.springboot.repository.CompanyChatRepository;
import com.fullstack.springboot.repository.DeptInfoRepository;
import com.fullstack.springboot.repository.DeptScheduleRepository;
import com.fullstack.springboot.repository.EmpScheuleRepository;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.repository.JobRepository;
import com.fullstack.springboot.repository.RoomListRepository;
import com.fullstack.springboot.repository.SalaryChartRepository;
import com.fullstack.springboot.service.CompanyChatService;
import com.fullstack.springboot.service.DeptScheduleService;
import com.fullstack.springboot.service.EmpScheduleService;

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
	private EmpScheuleRepository empScheuleRepository;

	@Autowired
	private DeptScheduleRepository deptScheduleRepository;

	@Autowired
	private EmpScheduleService empScheduleService;
	
	@Autowired
	private DeptScheduleService deptScheduleService;
	
	@Autowired
	private CompanyChatMemberRepository companyChatMemberRepository;
	
	@Autowired
	private CompanyChatRepository companyChatRepository;
	
	@Autowired
	private CompanyChatService companyChatService;
	
//	@Test
//	void delTest() {
//		long empNo = 3L;
//		long empSchNo = 338L;
//		
//		empScheuleRepository.deleteById(empSchNo);
//	}
	
	
//	@Test
//	@Transactional
//	 void testGetSche() {
//		log.error("Gggggggggggggg");
//		System.out.println("Gggggggggg");
//		
//		long empNo = 3L;
//	
//		List<EmpScheduleDTO> res = empScheuleRepository.getEmpScheduleDate(empNo, LocalDateTime.of(2024,12,23,15,41,26), LocalDateTime.of(2024,12,23,15,41,26));
//		if (res.isEmpty()) {
//		    log.error("error");
//		} else {
//		    for (EmpScheduleDTO dto : res) {
//		        log.error("res" + dto);
//		    }
//		}
//    }

	
//	@Test
//	void testGetDeptScheduleList() {
//	    long deptNo = 100L;
//	    long empNo = 3L;
//
//	    List<DeptScheduleDTO> result = deptScheduleRepository.getDeptScheduleList(deptNo, empNo);
//
//	    if (result.isEmpty()) {
//	        System.out.println("결과x");
//	    } else {
//	        for (DeptScheduleDTO dto : result) {
//	            System.out.println(dto);
//	        }
//	    }
//
//
//
//      
//	}


//	@Test
//	void test2() {
//		long empNo = 3;
//		
//		List<EmpScheduleDTO> res = empScheuleRepository.getEmpScheList(empNo);
//		if (res.isEmpty()) {
//		    log.error(empNo);
//		} else {
//		    for (EmpScheduleDTO dto : res) {
//		        log.error( dto);
//		    }
//		}
//	}
//}
//	}
	
//	@Test
//	void test3() {
//	    List<DeptScheduleDTO> one = deptScheduleRepository.getDeptScheOne(5L, 100L);
//	    for (DeptScheduleDTO dto : one) {
//	        log.error(dto.toString());  // 결과 출력
//	    }
//	}


	
//	@Test
//	void test4() {
//	 
//	    List<DeptScheduleDTO> result = deptScheduleRepository.getDeptScheOne(5L, 100L);
//
//
//	    if (result.isEmpty()) {
//	        System.out.println("결과x");
//	    } else {
//	        System.out.println(result);
//	    }
//	}

	
//	@Transactional
//	@Test
//	void test5() {
//	    List<DeptScheduleDTO> result = deptScheduleRepository.getDeptSchedulDay(100L, LocalDateTime.of(2024,12,23,15,43,37), LocalDateTime.of(2024,12,23,15,43,37));
//	    
//	    if (result.isEmpty()) {
//	        System.out.println("결과x");
//	    } else {
//	        System.out.println(result);
//	        for (DeptScheduleDTO dto : result) {
//	            System.out.println(dto.getEmpNo());  
//	        }
//	    }
//	}
//	


//	@Test
//	void tes() {
//		List<EmpScheduleDTO> res = empScheduleService.getEmpScheduleList(3L);
//		for(EmpScheduleDTO dto : res) {
//			log.warn(dto);
//			}
//		}
	
//	@Test
//	@Commit
//	void tes() { //글 수정하는 거 테스트
//		Long empSchNo = 228L;
//		String scheduleTest = "팀 미팅";
//		Optional<EmpSchedule> res = empScheuleRepository.findById(empSchNo);
//		EmpSchedule empSchedule = res.orElseThrow();
//		empSchedule.changeScheduleText(scheduleTest);
//		empScheuleRepository.save(empSchedule);
//		log.error("수정완료");
//	}
	
	
//	@Test
//	void insEmpSche() {
//		IntStream.rangeClosed(3, 100).forEach(i->{
//			Employees employees = Employees.builder().empNo(i).build();
//			EmpSchedule empSchedule = EmpSchedule.builder()
//					.scheduleText("팀회의")
//					.employees(employees)
//					.startDate(LocalDateTime.now())
//					.endDate(LocalDateTime.now())
//					.build();
//			empScheuleRepository.save(empSchedule);
//		});
//	}
	
//	@Autowired
//	private DeptScheduleRepository deptScheduleRepository;
//
//	
//	@Test
//	void DeptIns() {
//		Employees employees = Employees.builder().empNo(3).build();
//		DeptInfo deptInfo =  DeptInfo.builder().deptNo(100).build();
//		DeptSchedule deptSchedule = DeptSchedule.builder()
//				.scheduleText("Team meeting")
//				.deptInfo(deptInfo)
//				.employees(employees)
//				.startDate(LocalDateTime.now())
//				.endDate(LocalDateTime.now())
//				.build();
//		deptScheduleRepository.save(deptSchedule);
//	}
	
	

//	@Test
//	void tes() {
//		 CompanyChat companyChat = CompanyChat.builder()
//		            .companyChatMember(new ArrayList<>()) 
//		            .build();
//		    companyChatRepository.save(companyChat);  
//
//		    IntStream.rangeClosed(3, 13).forEach(i -> {
//		        Employees employees = employeesRepository.findById((long) i)
//		                .orElseThrow(() -> new RuntimeException("emp x"));
//		        CompanyChatMember chatMember = CompanyChatMember.builder()
//		                .employees(employees)   
//		                .companyChat(companyChat) 
//		                .build();
//
//		        companyChatMemberRepository.save(chatMember);
//		        companyChat.getCompanyChatMember().add(chatMember);
//		    });
//	}
	
	
//	@Test
//	void getCompanyChatNoAll() {
//		List<CompanyChatDTO> one = companyChatRepository.getCompanyChatNoAll(3L);
//		for(CompanyChatDTO dto : one) {
//			log.error(dto.toString());
//		}
//	}

//	@Test
//	void getChatAllMember(){
//		List<CompanyChatMemberDTO> one = companyChatMemberRepository.getChatAllMember(4L);
//		for(CompanyChatMemberDTO dto : one) {
//			log.error(dto);
//		}
//	}


//	@Test
//	void getCompanyChatNoOne() {
//		List<CompanyChatDTO> one = companyChatRepository.getCompanyChatNoOne(3L, 4L);
//		for(CompanyChatDTO dto : one) {
//			log.error(dto);
//		}
//	}

//	@Test
//	void getChatOneMember() {
//		List<CompanyChatMemberDTO> one = companyChatMemberRepository.getChatOneMember(4L, 3L);
//		for(CompanyChatMemberDTO dto : one) {
//			log.error(dto);
//		}
//	}
	
//	@Test
//	void tee() {
//		String chatNo = "3_89";
//		Long empNo = 3L;
//		List<CompanyChatMemberDTO> res = companyChatMemberRepository.getSameList(chatNo, empNo);
//		for(CompanyChatMemberDTO dto : res) {
//			log.warn(dto);
//		}
//	
//	}
	
	@Test
	void tes() {
		int year = LocalDate.now().getYear();
		String DIRECTORY_PATH = "C:" + File.separator + "chatting" + File.separator + year;
		String chatNo = "3_95";
		try {
			List<List<String>> res = companyChatService.chatDataReadToExcel(DIRECTORY_PATH, chatNo);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	}

