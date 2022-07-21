package com.work.workhub.member.report.controller;



import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.member.member.dto.UserImpl;
import com.work.workhub.member.report.model.dto.ReportDTO;
import com.work.workhub.member.report.model.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("report")
public class ReportController {
	
	private ReportService reportService;
	private MessageSource message;
	
	@Autowired
	public ReportController(ReportService reportService, MessageSource message) {
		this.reportService = reportService;
		this.message = message;
	}
	
	@GetMapping("intro")
	public void reportIntro() {
		
	}
	
	@GetMapping("create")
	public void registReport() {
		
	}

	@PostMapping("create")
	public String registReport(Model model, @ModelAttribute ReportDTO report, RedirectAttributes rttr, Locale locale, 
			@RequestParam int memberNo, @RequestParam int depNo, @RequestParam(value="multiFiles", required=false) MultipartFile[] files) throws Exception {
		
		log.info("등록요청 : {}", report);
		log.info("넘어온 file : {}", files[0]);
		
		
		reportService.registReport(report, files);
		
		
		rttr.addFlashAttribute("successMessage", message.getMessage("registReport", null, locale));
		
		return "redirect:/report/myreport";
	}
	
	@GetMapping("myreport")
	public ModelAndView selectReportList(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		
		log.info("로그인 유저 정보 : {}", user);
		int no = user.getNo();

		List<ReportDTO> myReportList = reportService.selectMyList(no);
		
		mv.addObject("myReportList", myReportList);
		log.info("내 보고서 정보 : {}", myReportList);

		
		mv.setViewName("report/myreport");
		
		return mv;
	}
	
	@GetMapping("dept")
	public ModelAndView selectDeptReport(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		
		log.info("로그인 유저 정보 : {}", user);
		int depNo = user.getDepNo();

		List<ReportDTO> deptReportList = reportService.selectDeptList(depNo);
		
		mv.addObject("deptReportList", deptReportList);
		log.info("부서 보고서 정보 : {}", deptReportList);

		
		mv.setViewName("report/dept");
		
		return mv;
	}
	
}
