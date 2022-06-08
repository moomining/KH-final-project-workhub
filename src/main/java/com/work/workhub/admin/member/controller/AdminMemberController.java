package com.work.workhub.admin.member.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.admin.member.model.service.AdminMemberService;
import com.work.workhub.member.member.dto.DepartmentDTO;
import com.work.workhub.member.member.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminMemberController {

	private AdminMemberService adminMemberService;
	private MessageSource messageSource;

	@Autowired
	public AdminMemberController(AdminMemberService adminMemberService, MessageSource messageSource) {
		this.adminMemberService = adminMemberService;
		this.messageSource = messageSource;
	}

	@GetMapping("/signUp")
	public String signUpPage() {
		
		return "admin/member/signUp";
	}
	
	@GetMapping(value="/member/memberList")
	public ModelAndView memberListPage(ModelAndView mv) {
		
		List<MemberDTO> memberList = adminMemberService.memberList();
		mv.addObject("memberList", memberList);
		
		List<DepartmentDTO> depList = adminMemberService.depList();
		mv.addObject("depList", depList);
		
		mv.setViewName("admin/member/memberList");
		
		return mv;
	}

	@ResponseBody
	@RequestMapping(value="/member/memberListAjax")
	public List<MemberDTO> memberListAjax(@RequestParam("depNo") String depNo) {
		System.out.println("==========depNo ; "+depNo);
		List<MemberDTO> memberList = adminMemberService.memberList(depNo);
		return memberList;
	}
	
	@PostMapping("/signUp")
	public String signUp(MemberDTO member, RedirectAttributes rttr, Locale locale) {
		
		log.error("사원 등록 : {}", member);
		log.info("사원 등록 : {}", member);		
		
		adminMemberService.signUp(member);
		
		rttr.addFlashAttribute("succesMessage", messageSource.getMessage("signUp", null, locale));
		
		return "redirect:/admin/member/memberList";
	}
	
}
