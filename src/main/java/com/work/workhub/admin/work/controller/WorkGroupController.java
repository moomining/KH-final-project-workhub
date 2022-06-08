package com.work.workhub.admin.work.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.admin.work.model.dto.RestCateDTO;
import com.work.workhub.admin.work.model.dto.WorkDTO;
import com.work.workhub.admin.work.model.dto.WorkGroupDTO;
import com.work.workhub.admin.work.model.service.WorkGroupService;
import com.work.workhub.member.member.dto.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/group")
public class WorkGroupController {
	
	private WorkGroupService workgroupService;
	private MessageSource messageSource;
	
	@Autowired
	public WorkGroupController(WorkGroupService workgroupService, MessageSource messageSource) {
		this.workgroupService = workgroupService;
		this.messageSource = messageSource;
	}
	
	
	
	/* 근무그룹유형 조회 status = 'y'*/		
	
	@GetMapping("list")
	public ModelAndView workGroupSelect(ModelAndView mv) {
			
		List<WorkGroupDTO> workGroupList = workgroupService.selectAllworkGroup();
		

		mv.addObject("workGroupList",workGroupList);
		
		mv.setViewName("group/workGroupSelect");
		
		return mv;
	}
	
	 //근무그룹 유형 작성 폼
		@GetMapping("workGroupWrite")
		public void workGroupWrite() {}
		
		@PostMapping("workGroupWrite")
		public String workGroupWrite(@ModelAttribute WorkGroupDTO post, @AuthenticationPrincipal UserImpl user ,RedirectAttributes rttr, Locale locale) throws Exception {
			
			
			post.setWriteNo(user.getNo());
			log.error("등록 요청 글 : {}", post);
			log.warn("등록 요청 글 : {}", post);
			log.info("등록 요청 글 : {}", post);
			log.debug("등록 요청 글 : {}", post);
			log.trace("등록 요청 글 : {}", post);
			
			workgroupService.workGroupWrite(post);
			
			rttr.addFlashAttribute("successMessage", messageSource.getMessage("workGroupWrite", null, locale));
			
			return "redirect:/group/list";
			
		}
		
		  //근무그룹 수정 예전 값 보기
			@GetMapping("workGroupModify")
			public ModelAndView workGroupModify(@RequestParam int groupNo, ModelAndView mv) {
					
				List<WorkGroupDTO> workGroupMo = workgroupService.ModifyworkGroup(groupNo);
				
				
				mv.addObject("workGroupMo",workGroupMo);
				
				System.out.println(workGroupMo);
				
				mv.setViewName("group/workGroupModify");
				
				return mv;
				
			}		
		
			//근무그룹 수정
			@PostMapping("workGroupModifyGo")
			public String workGroupModifyGo(@ModelAttribute WorkGroupDTO post, @AuthenticationPrincipal UserImpl user, RedirectAttributes rttr, Locale locale) throws Exception {
				
				post.setWriteNo(user.getNo());

				log.info("등록 요청 글 : {}", post);

				
				workgroupService.workGroupModifyGo(post);
				
				rttr.addFlashAttribute("successMessage", messageSource.getMessage("workGroupModifyGo", null, locale));
				
				return "redirect:/group/list";
				
			}		
		
			//근무유형 삭제
			@GetMapping("workGroupDelete")
			public String workGroupDelete(@RequestParam int groupNo ,@ModelAttribute WorkGroupDTO post, @AuthenticationPrincipal UserImpl user, RedirectAttributes rttr, Locale locale) throws Exception {
				
				post.setWriteNo(user.getNo());
				
				log.info("삭제 요청 글 : {}", post);
				
				
				workgroupService.workGroupDelete(post, groupNo);
				
				rttr.addFlashAttribute("successMessage", messageSource.getMessage("workGroupDelete", null, locale));
				
				return "redirect:/group/list";
				
			}			
			
			
		/* 근무그룹유형 조회 use = '사용' 만*/		
		
//		@GetMapping("lists")
//		public ModelAndView workGroupSelects(ModelAndView mv) {
//				
//			List<WorkGroupDTO> workGroupLists = workgroupService.selectAllworkGroups();
//			
//
//			mv.addObject("workGroupLists",workGroupLists);
//			
//			mv.setViewName("group/workGroupWrite");
//			
//			return mv;
//		}

	
	
}
