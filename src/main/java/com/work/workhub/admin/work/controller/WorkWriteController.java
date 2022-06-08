package com.work.workhub.admin.work.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.admin.work.model.dto.WorkDTO;
import com.work.workhub.admin.work.model.service.WorkService;
import com.work.workhub.member.member.dto.MemberDTO;
import com.work.workhub.member.member.dto.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/work")
public class WorkWriteController {
	
	private WorkService workService;
	private MessageSource messageSource;
	
	@Autowired
	public WorkWriteController(WorkService workService, MessageSource messageSource) {
		this.workService = workService;
		this.messageSource = messageSource;
	}
	
	
	
	/* 근무유형 조회*/		
	
	@GetMapping("list")
	public ModelAndView workSelect(ModelAndView mv) {
			
		List<WorkDTO> workList = workService.selectAllwork();
		
		mv.addObject("workList",workList);
		
		mv.setViewName("work/workSelect");
		
		return mv;
	}
	
	
	//근무 유형 작성 폼
	@GetMapping("workWrite")
	public void workWrite() {}
	
	@PostMapping("workWrite")
	public String workWrite(@ModelAttribute WorkDTO post, @AuthenticationPrincipal UserImpl user, RedirectAttributes rttr, Locale locale) throws Exception {
		
		post.setWriteNo(user.getNo());
		log.error("등록 요청 글 : {}", post);
		log.warn("등록 요청 글 : {}", post);
		log.info("등록 요청 글 : {}", post);
		log.debug("등록 요청 글 : {}", post);
		log.trace("등록 요청 글 : {}", post);
		
		workService.workWrite(post);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("workWrite", null, locale));
		
		return "redirect:/work/list";
		
	}

	  //근무유형 수정 예전 값 보기
		@GetMapping("workModify")
		public ModelAndView workModify(@RequestParam int workNo, ModelAndView mv) {
				
			List<WorkDTO> workMo = workService.Modifywork(workNo);
			
			
			mv.addObject("workMo",workMo);
			
			System.out.println(workMo);
			
			mv.setViewName("work/workModify");
			
			return mv;
			
		}
		
		
		//근무유형 수정
		@PostMapping("workModifyGo")
		public String workModifyGo(@ModelAttribute WorkDTO post, @AuthenticationPrincipal UserImpl user, RedirectAttributes rttr, Locale locale) throws Exception {
			
			post.setWriteNo(user.getNo());

			log.info("등록 요청 글 : {}", post);

			
			workService.workModifyGo(post);
			
			rttr.addFlashAttribute("successMessage", messageSource.getMessage("workModifyGo", null, locale));
			
			return "redirect:/work/list";
			
		}
		
		
		//근무유형 삭제
		@GetMapping("workDelete")
		public String workDelete(@RequestParam int workNo ,@ModelAttribute WorkDTO post, @AuthenticationPrincipal UserImpl user, RedirectAttributes rttr, Locale locale) throws Exception {
			
			post.setWriteNo(user.getNo());
			
			log.info("삭제 요청 글 : {}", post);
			
			
			workService.workDelete(post, workNo);
			
			rttr.addFlashAttribute("successMessage", messageSource.getMessage("workDelete", null, locale));
			
			return "redirect:/work/list";
			
		}
	
}
