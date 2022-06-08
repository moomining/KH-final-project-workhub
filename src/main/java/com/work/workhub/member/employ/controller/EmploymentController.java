package com.work.workhub.member.employ.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.admin.work.model.dto.MemberGroupDTO;
import com.work.workhub.admin.work.model.dto.RestCateDTO;
import com.work.workhub.admin.work.model.dto.WorkDTO;
import com.work.workhub.admin.work.model.dto.WorkGroupDTO;
import com.work.workhub.member.employ.model.dto.AttDTO;
import com.work.workhub.member.employ.model.dto.RestDTO;
import com.work.workhub.member.employ.model.service.EmploymentService;
import com.work.workhub.member.member.dto.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/employ")
public class EmploymentController {
	
	private EmploymentService employmentService;
	private MessageSource messageSource;
	
	@Autowired
	public EmploymentController(EmploymentService employmentService, MessageSource messageSource) {
		this.employmentService = employmentService;
		this.messageSource = messageSource;
	}
	
	/* 근무유형 select box*/
	@GetMapping("employmentSelect")
	public ModelAndView employmentSelect(ModelAndView mv) {
		
		//근무 유형 select box
		List<WorkDTO> attList = employmentService.selectBox();
		
		//내 근태 조회
		List<AttDTO> myList = employmentService.selectMy();

		mv.addObject("attList",attList);
		mv.addObject("myList",myList);
		
		
		mv.setViewName("employ/employmentSelect");
		
		return mv;
	}
		
		
		//출근하기 버튼 눌렀을 때
		@GetMapping("in")
		public String employmentIn(@ModelAttribute AttDTO post, @AuthenticationPrincipal UserImpl user, RedirectAttributes rttr, Locale locale) throws Exception {
			
			post.setNo(user.getNo());
			
			log.info("등록 요청 글 : {}", post);
			
			
			employmentService.employmentIn(post);
			
			rttr.addFlashAttribute("successMessage", messageSource.getMessage("employmentIn", null, locale));
			
			return "redirect:/employ/employmentSelect";
			
		}
		
		//퇴근하기 버튼 눌렀을 때
		@GetMapping("out")
		public String employmentOut(@ModelAttribute AttDTO post, @AuthenticationPrincipal UserImpl user, RedirectAttributes rttr, Locale locale) throws Exception {
			
			post.setNo(user.getNo());
			
			log.info("등록 요청 글 : {}", post);
			
			
			employmentService.employmentOut(post);
			
			rttr.addFlashAttribute("successMessage", messageSource.getMessage("employmentOut", null, locale));
			
			return "redirect:/employ/employmentSelect";
			
		}
		
				
		//근태 조회
		@GetMapping("employmentSelects")
		public ModelAndView employmentSelects(ModelAndView mv) {
			
			
			//근태 조회
			List<AttDTO> attenList = employmentService.attenSelect();
			

			mv.addObject("attenList",attenList);
			
			mv.setViewName("employ/employmentSelect");
			
			return mv;
		}
		
		//select box 근무 등록
		@PostMapping("other")
		public String employmentOther(@ModelAttribute AttDTO post, @AuthenticationPrincipal UserImpl user ,RedirectAttributes rttr, Locale locale) throws Exception {
			
			post.setNo(user.getNo());
			
			log.info("등록 요청 글 : {}", post);
			
			
			employmentService.employmentOther(post);
			
			rttr.addFlashAttribute("successMessage", messageSource.getMessage("employmentOther", null, locale));
			
			return "redirect:/employ/employmentSelect";
			
		}
		
		//근태관리 수정 옛날값
		@GetMapping("employmentModify")
		public ModelAndView employmentModify(ModelAndView mv, @RequestParam int attNo) {
			
			//내 근태 예전 값
			List<AttDTO> atList = employmentService.beforedData(attNo);
			
			//근태 유형 select box
			List<RestCateDTO> sList = employmentService.sBox();

			mv.addObject("atList",atList);
			mv.addObject("sList",sList);

			mv.setViewName("employ/employmentModify");
			
			return mv;
			
		}
		
		//근태관리 수정
		@PostMapping("employmentModifyGo")
		public String employmentModifyGo(@ModelAttribute AttDTO post, @AuthenticationPrincipal UserImpl user ,RedirectAttributes rttr, Locale locale) throws Exception {
			
			
			post.setNo(user.getNo());
			
			

			log.info("등록 요청 글 : {}", post);

			employmentService.employmentModifyGo(post);
			
			rttr.addFlashAttribute("successMessage", messageSource.getMessage("employmentModifyGo", null, locale));
			
			return "redirect:/employ/employmentSelect";
			
		}
		
		
		
		//근태관리 삭제
		@GetMapping("employDelete")
		public String employDelete(@RequestParam int attNo ,@ModelAttribute AttDTO post, @AuthenticationPrincipal UserImpl user, RedirectAttributes rttr, Locale locale) throws Exception {
			
			post.setNo(user.getNo());
			
			log.info("삭제 요청 글 : {}", post);
			
			
			employmentService.employDelete(post, attNo);
			
			rttr.addFlashAttribute("successMessage", messageSource.getMessage("employDelete", null, locale));
			
			return "redirect:/employ/employmentSelect";
			
		}	
		
		
		
		//근태 엑셀 내보내기
		@GetMapping("excelDown")
		public void excelDown(HttpServletResponse response) throws Exception {


		    // 근태 조회

		    List<AttDTO> excelList = employmentService.excelDown();


		    // 워크북 생성

		    Workbook wb = new HSSFWorkbook();

		    Sheet sheet = wb.createSheet("게시판");

		    Row row = null;

		    Cell cell = null;

		    int rowNo = 0;



		    // 테이블 헤더용 스타일

		    CellStyle headStyle = wb.createCellStyle();

		    // 가는 경계선을 가집니다.

		    headStyle.setBorderTop(BorderStyle.THIN);

		    headStyle.setBorderBottom(BorderStyle.THIN);

		    headStyle.setBorderLeft(BorderStyle.THIN);

		    headStyle.setBorderRight(BorderStyle.THIN);



		    // 배경색은 노란색입니다.

		    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());

		    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);



		    // 데이터는 가운데 정렬합니다.

		    headStyle.setAlignment(HorizontalAlignment.CENTER);
		    
		    //날짜 데이터 값 깨지는거 방지
		    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		    

		    // 데이터용 경계 스타일 테두리만 지정

		    CellStyle bodyStyle = wb.createCellStyle();

		    bodyStyle.setBorderTop(BorderStyle.THIN);

		    bodyStyle.setBorderBottom(BorderStyle.THIN);

		    bodyStyle.setBorderLeft(BorderStyle.THIN);

		    bodyStyle.setBorderRight(BorderStyle.THIN);



		    // 헤더 생성

		    row = sheet.createRow(rowNo++);

		    cell = row.createCell(0);

		    cell.setCellStyle(headStyle);

		    cell.setCellValue("근태관리번호");

		    cell = row.createCell(1);

		    cell.setCellStyle(headStyle);

		    cell.setCellValue("이름");

		    cell = row.createCell(2);

		    cell.setCellStyle(headStyle);

		    cell.setCellValue("근무유형");
		    
		    cell = row.createCell(3);

		    cell.setCellStyle(headStyle);

		    cell.setCellValue("시간");
		    
		    cell = row.createCell(4);

		    cell.setCellStyle(headStyle);

		    cell.setCellValue("수정 이유");
		    
		    cell = row.createCell(5);

		    cell.setCellStyle(headStyle);

		    cell.setCellValue("작성일");
		    
		    cell = row.createCell(6);

		    cell.setCellStyle(headStyle);

		    cell.setCellValue("수정일");



		    // 데이터 부분 생성

		    for(AttDTO ex : excelList) {

		        row = sheet.createRow(rowNo++);

		        cell = row.createCell(0);

		        cell.setCellStyle(bodyStyle);

		        cell.setCellValue(ex.getAttNo());

		        cell = row.createCell(1);

		        cell.setCellStyle(bodyStyle);

		        cell.setCellValue(ex.getName());

		        cell = row.createCell(2);

		        cell.setCellStyle(bodyStyle);

		        cell.setCellValue(ex.getWorkName());
		        
		        cell = row.createCell(3);

		        cell.setCellStyle(bodyStyle);

		        cell.setCellValue(ex.getAttGo());
		        
		        cell = row.createCell(4);

		        cell.setCellStyle(bodyStyle);

		        cell.setCellValue(ex.getAttReason());
		        
		        cell = row.createCell(5);

		        cell.setCellStyle(bodyStyle);
		        
		        //날짜 형식 값은 가져갔을때 깨져서 format 처리 했음
		        cell.setCellValue(ex.getAttDate());
		        
		        cell = row.createCell(6);

		        cell.setCellStyle(bodyStyle);
		        
		        //format을 하니까 null 값인 경우 오류가 나서 null이 아닌 경우에만 format 하도록 if문에 넣음 
		        cell.setCellValue(ex.getAttModdate());
		       
		   
		    }


		    // 컨텐츠 타입과 파일명 지정 인코딩 처리까지..

		    String fileName = "근태관리.xls";
		    fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		    response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";");
		    response.setHeader("Content-Transfer-Encoding", "binary");


		    // 엑셀 출력

		    wb.write(response.getOutputStream());

		    wb.close();

		}

		
		
		
		
		
}
