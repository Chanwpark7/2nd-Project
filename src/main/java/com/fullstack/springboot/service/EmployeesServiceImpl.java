package com.fullstack.springboot.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fullstack.springboot.dto.DayOffDTO;
import com.fullstack.springboot.dto.DeptInfoDTO;
import com.fullstack.springboot.dto.EmployeesDTO;
import com.fullstack.springboot.dto.JobDTO;
import com.fullstack.springboot.dto.PageRequestDTO;
import com.fullstack.springboot.dto.PageResponseDTO;
import com.fullstack.springboot.entity.DeptInfo;
import com.fullstack.springboot.entity.Employees;
import com.fullstack.springboot.repository.DeptInfoRepository;
import com.fullstack.springboot.repository.EmployeesRepository;
import com.fullstack.springboot.repository.JobRepository;
import com.fullstack.springboot.service.annualleave.AnnualleaveService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmployeesServiceImpl implements EmployeesService {
	
	private final ModelMapper modelMapper;
	
	private final EmployeesRepository employeesRepository;
	private final DeptInfoRepository deptInfoRepository;
	private final JobRepository jobRepository;
	private final PasswordEncoder pwEncoder;
	private final AnnualleaveService annualleaveService;
	
	@Override
	public EmployeesDTO addEmployees(EmployeesDTO employeesDTO) {
		
		Employees employees = Employees.builder()
				.firstName(employeesDTO.getFirstName())
				.lastName(employeesDTO.getLastName())
				.hireDate(employeesDTO.getHireDate())
				.mailAddress(employeesDTO.getMailAddress())
				.salary(employeesDTO.getSalary())
				.deptInfo(deptInfoRepository.findById(employeesDTO.getDeptNo()).get())
				.job(jobRepository.findById(employeesDTO.getJobNo()).get())
				.birthday(employeesDTO.getBirthday())
				.address(employeesDTO.getAddress())
				.phoneNum(employeesDTO.getPhoneNum())
				.gender(employeesDTO.getGender())
				.citizenId(employeesDTO.getCitizenId())
				.password(pwEncoder.encode(employeesDTO.getPassword()))
				.build();
		
		employeesRepository.save(employees);
		
		Long empNo = Long.parseLong(employeesRepository.getMaxEmpNo().toString());

		
		Employees emp = employeesRepository.findById(empNo).get();
		
		EmployeesDTO dto = EmployeesDTO.builder()
				.empNo(emp.getEmpNo())
				.firstName(emp.getFirstName())
				.lastName(emp.getLastName())
				.hireDate(emp.getHireDate())
				.mailAddress(emp.getMailAddress())
				.salary(emp.getSalary())
				.deptNo(emp.getDeptInfo().getDeptNo())
				.jobNo(emp.getJob().getJobNo())
				.birthday(emp.getBirthday())
				.address(emp.getAddress())
				.phoneNum(emp.getPhoneNum())
				.gender(emp.getGender())
				.citizenId(emp.getCitizenId())
				.build();
		
		return dto;
	}
	
	@Override
	public EmployeesDTO modifyEmployees(EmployeesDTO employeesDTO) {
		//hiredate, birthday 수정 안됨
		Employees employees = Employees.builder()
				.empNo(employeesDTO.getEmpNo())
				.firstName(employeesDTO.getFirstName())
				.lastName(employeesDTO.getLastName())
				.hireDate(employeesDTO.getHireDate())
				.mailAddress(employeesDTO.getMailAddress())
				.salary(employeesDTO.getSalary())
				.deptInfo(deptInfoRepository.findById(employeesDTO.getDeptNo()).get())
				.job(jobRepository.findById(employeesDTO.getJobNo()).get())
				.birthday(employeesDTO.getBirthday())
				.address(employeesDTO.getAddress())
				.phoneNum(employeesDTO.getPhoneNum())
				.gender(employeesDTO.getGender())
				.citizenId(employeesDTO.getCitizenId())
				.password(pwEncoder.encode(employeesDTO.getPassword()))
				.build();
		
		employeesRepository.save(employees);
				
		return employeesDTO;
	}

	@Override
	public void deleteEmployees(Long empNo) {

		Employees employees = employeesRepository.findById(empNo).get();
		
		employees.changePw(pwEncoder.encode("deleted"));
		
		employeesRepository.save(employees);
	}

	@Override
	public PageResponseDTO<EmployeesDTO> getEmployeesListPage(PageRequestDTO pageRequestDTO) {
		
		Pageable pageable = pageRequestDTO.getPageable(Sort.by("empNo").ascending());
		
		Page<EmployeesDTO> page = employeesRepository.getEmployeesList(pageable);
		
		List<EmployeesDTO> dtoList = page.get().toList();
		
		long totalCount = page.getTotalElements();
		
		return PageResponseDTO.<EmployeesDTO>withAll()
				.dtoList(dtoList)
				.totalCount(totalCount)
				.pageRequestDTO(pageRequestDTO)
				.build();
	}

	@Override
	public EmployeesDTO getOne(Long empNo) {
		
		Optional<Employees> result = employeesRepository.findById(empNo);
		
		Employees emp = result.orElseThrow();

		EmployeesDTO dto = EmployeesDTO.builder()
				.empNo(emp.getEmpNo())
				.firstName(emp.getFirstName())
				.lastName(emp.getLastName())
				.hireDate(emp.getHireDate())
				.mailAddress(emp.getMailAddress())
				.salary(emp.getSalary())
				.deptNo(emp.getDeptInfo().getDeptNo())
				.jobNo(emp.getJob().getJobNo())
				.birthday(emp.getBirthday())
				.address(emp.getAddress())
				.phoneNum(emp.getPhoneNum())
				.gender(emp.getGender())
				.citizenId(emp.getCitizenId())
				.build();
		
		return dto;
	}
	@Override
	public long getDDay(long empNo) {
		Employees employees = employeesRepository.getEmpNo(empNo).orElseThrow();
		LocalDate hirDate = employees.getHireDate();
		LocalDate curDate = LocalDate.now();
		
		return ChronoUnit.DAYS.between(hirDate, curDate);
	}

	@Override
	public PageResponseDTO<EmployeesDTO> getBirthEmp(PageRequestDTO pageRequestDTO) {
	    Pageable pageable = pageRequestDTO.getPageable(Sort.by("empNo").ascending());

	    Page<EmployeesDTO> page = employeesRepository.getEmployeesList(pageable);

	    List<EmployeesDTO> dtoList = page.get().toList();
	    long totalCount = page.getTotalElements();

	    LocalDate nowDate = LocalDate.now();
	    int currentMonth = nowDate.getMonthValue();
	    int currentDay = nowDate.getDayOfMonth(); 

	    List<EmployeesDTO> birthdayEmployees = dtoList.stream()
	            .filter(emp -> {
	                LocalDate birthDate = emp.getBirthday(); 
	                return birthDate != null && birthDate.getMonthValue() == currentMonth && birthDate.getDayOfMonth() == currentDay;
	            })
	            .collect(Collectors.toList());

	    if (birthdayEmployees.isEmpty()) {
	        return null; 
	    }

	    return PageResponseDTO.<EmployeesDTO>withAll()
	            .dtoList(birthdayEmployees)
	            .totalCount((long) birthdayEmployees.size())
	            .pageRequestDTO(pageRequestDTO)
	            .build();
	}

	@Override
	public List<EmployeesDTO> addAllList() {

		return employeesRepository.getAllList();
	}
	
	@Override
	public void downloadExcelForm(HttpServletResponse res){
		String fileName = "EmployeesRegisterForm";
		
		List<DeptInfoDTO> depts = deptInfoRepository.getDeptList();
		
		List<JobDTO> jobs = jobRepository.getJobList();
		
		String extistingJobs = "";

		String extistingDepts = "";
		
		for(DeptInfoDTO dept:depts) {
			extistingDepts = extistingDepts + dept.getDeptName() + "/";
		}
		
		for(JobDTO job:jobs) {
			extistingJobs = extistingJobs + job.getJobTitle() + "/";
		}
		
		extistingDepts = extistingDepts.substring(0,extistingDepts.lastIndexOf("/"));
		extistingJobs = extistingJobs.substring(0,extistingJobs.lastIndexOf("/"));
		
		//엑셀 파일 생성
		Workbook workbook = new XSSFWorkbook();
		
		//내부에 sheet 생성
		Sheet sheet = workbook.createSheet("직원일괄등록폼");
		
		//엑셀 렌더링 필요한 DTO 생성
		
		//헤더 생성
		int rowIndex = 0;
		int cellIndex = 0;
		Row headerRow = sheet.createRow(rowIndex++);
		
		Cell headerCell1 = headerRow.createCell(cellIndex++);
		headerCell1.setCellValue("성");
		
		Cell headerCell2 = headerRow.createCell(cellIndex++);
		headerCell2.setCellValue("이름");
		
		Cell headerCell3 = headerRow.createCell(cellIndex++);
		headerCell3.setCellValue("메일주소");
		
		Cell headerCell4 = headerRow.createCell(cellIndex++);
		headerCell4.setCellValue("급여");
		
		Cell headerCell5 = headerRow.createCell(cellIndex++);
		headerCell5.setCellValue("부서("+extistingDepts+")");
		
		Cell headerCell6 = headerRow.createCell(cellIndex++);
		headerCell6.setCellValue("직책("+extistingJobs+")");
		
		Cell headerCell7 = headerRow.createCell(cellIndex++);
		headerCell7.setCellValue("생일(xxxx-xx-xx)");
		
		Cell headerCell8 = headerRow.createCell(cellIndex++);
		headerCell8.setCellValue("주소");
		
		Cell headerCell9 = headerRow.createCell(cellIndex++);
		headerCell9.setCellValue("전화번호(-제외하고 입력)");
		
		Cell headerCell10 = headerRow.createCell(cellIndex++);
		headerCell10.setCellValue("성별(남자/여자)");
		
		Cell headerCell11 = headerRow.createCell(cellIndex++);
		headerCell11.setCellValue("주민등록번호(-제외하고 입력)");
		
		Cell headerCell12 = headerRow.createCell(cellIndex++);
		headerCell12.setCellValue("비밀번호");
		
		res.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        res.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName+".xlsx");

        // 파일을 출력 스트림으로 작성
        try {
            workbook.write(res.getOutputStream());
            workbook.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Override
	public int readExcelFile(MultipartFile file) {
		
		int count = 0;
		
		try {
			Workbook workbook = new XSSFWorkbook(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			
			EmployeesDTO employeesDTO;
			
			int lastRowNum = sheet.getLastRowNum();
			
			for(int rowIndex = 1;rowIndex<=lastRowNum;rowIndex++) {
				Row cells = sheet.getRow(rowIndex);
				employeesDTO = createDTOFromCells(cells);
				addEmployees(employeesDTO);
				annualleaveService.setAnnualleave(employeesRepository.getByEmail(employeesDTO.getMailAddress()).getEmpNo());
				count++;
			}

			return count;
		} catch (Exception e) {
			e.getMessage();
		}

		return count;
	}
	
	private EmployeesDTO createDTOFromCells(Row cells) {
		String gen = getStringCellValue(cells, 9);

		if(gen.equals("남자")) {
			gen = "m";
		}else {
			gen = "f";
		}
		
		Long sal = Long.parseLong(getStringCellValue(cells, 3));
		Long deptNo = deptInfoRepository.getDeptInfoWithName(getStringCellValue(cells, 4)).getDeptNo();
		Long job = jobRepository.getJobWithTitle(getStringCellValue(cells, 5)).getJobNo(); 
		
		EmployeesDTO employeesDTO = EmployeesDTO.builder()
				.firstName(getStringCellValue(cells, 0))
				.lastName(getStringCellValue(cells, 1))
				.mailAddress(getStringCellValue(cells, 2))
				.salary(sal)
				.deptNo(deptNo)
				.jobNo(job)
				.birthday(LocalDate.parse(getStringCellValue(cells, 6)))
				.address(getStringCellValue(cells, 7))
				.phoneNum(getStringCellValue(cells, 8))
				.gender(gen)
				.citizenId(getStringCellValue(cells, 10))
				.password(getStringCellValue(cells, 11))
				.build();
		
		return employeesDTO;
	}
	
	private String getStringCellValue(Row row, int cellIndex) {
		Cell cell = row.getCell(cellIndex);
		if(cell != null) {
			if(cell.getCellType()==CellType.STRING) {
				return cell.getStringCellValue();
			}
			else if(cell.getCellType()==CellType.NUMERIC){
				Double num = cell.getNumericCellValue();
				Long number = num.longValue();
				return number.toString();
			}
		}
		return null;
	}
	
}
