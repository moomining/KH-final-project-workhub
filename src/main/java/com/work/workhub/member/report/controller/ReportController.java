package com.work.workhub.member.report.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

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
import com.work.workhub.member.report.model.dto.RepAttachDTO;
import com.work.workhub.member.report.model.dto.ReportDTO;
import com.work.workhub.member.report.model.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("report")
public class ReportController {
	
	private ReportService reportService;
	private MessageSource message;
	private String uploadFilePath;
	
	@Autowired
	public ReportController(ReportService reportService, MessageSource message, @Value("${custom.path.upload-files}")String uploadFilePath) {
		this.reportService = reportService;
		this.message = message;
		this.uploadFilePath = uploadFilePath;
	}
	
	@GetMapping("intro")
	public void reportIntro() {
		
	}
	
	@GetMapping("create")
	public void registReport() {
		
	}

	@PostMapping("create")
	public String registReport(@RequestParam(value="multiFiles", required=false) List<MultipartFile> multiFiles, Model model, @ModelAttribute ReportDTO report, RedirectAttributes rttr, Locale locale, 
			@RequestParam int memberNo, @RequestParam int depNo) throws Exception {
		
		
		log.info("등록요청 : {}", report);
		
		
		if (!multiFiles.isEmpty()) {
			System.out.println("multiFiles : " + multiFiles);
	
			// file 저장 경로 설정
			File mkdir = new File(uploadFilePath);
			if (!mkdir.exists()) {
				mkdir.mkdirs();
			}
			
			List<Map<String, String>> files = new ArrayList<>();
			
			
	
			// file name change
			for(int i = 0; i < multiFiles.size(); i++) {
				String orgName = multiFiles.get(i).getOriginalFilename();
				String ext = orgName.substring(orgName.lastIndexOf("."));
				String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
	
				//file에 관한 정보 추출 후 보관
				Map<String, String> file = new HashMap<>();
				file.put("orgName", orgName);
				file.put("savedName", savedName);
				file.put("uplaodFilePath", uploadFilePath);
				
				RepAttachDTO repAttach = new RepAttachDTO();
				repAttach.setOrgName(orgName);
				repAttach.setSavedName(savedName);
				repAttach.setSavePath(uploadFilePath);
				
				files.add(file);
				
			}
			
	
			// file Save
			try {
				
				for(int i = 0; i < multiFiles.size(); i++) {
					multiFiles.get(i).transferTo(new File(uploadFilePath + "\\" + files.get(i).get("savedName")));
				}
				
				
				model.addAttribute("message", "파일 업로드 성공!");
	
			} catch (IllegalStateException | IOException e) {
				
				for(int i = 0; i < multiFiles.size() ; i++) {
					
					new File(uploadFilePath + "\\" + files.get(i).get("savedName")).delete();
				}
				model.addAttribute("message", "파일 업로드 실패!");
			}
		
		}
		
		
		reportService.registReport(report);
		
		
		
		
		rttr.addFlashAttribute("successMessage", message.getMessage("registReport", null, locale));
		
		return "redirect:/report/mylist";
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
}
