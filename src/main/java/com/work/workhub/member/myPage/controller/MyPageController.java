package com.work.workhub.member.myPage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.work.workhub.member.member.dto.MemberDTO;
import com.work.workhub.member.member.dto.UserImpl;
import com.work.workhub.member.member.service.MemberService;
import com.work.workhub.member.member.service.MemberServiceImpl;
import com.work.workhub.member.myPage.model.service.MyPageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/myPage")
public class MyPageController {

	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private MemberService memberService;
	
	
	// 마이페이지 이동
	@RequestMapping(value = "/myPage")
	public ModelAndView myPage(@AuthenticationPrincipal UserImpl user, ModelAndView mv) throws Exception{
		
		String username = user.getId();
		MemberDTO member = myPageService.findMemberById(username);

		mv.setViewName("/myPage/myPage");
		mv.addObject("member", member);
		
		return mv;
		
	}

	
	// myPage 수정
	@ResponseBody
	@PostMapping(value = "/editInfo")
	public String editInfo(@AuthenticationPrincipal UserImpl user, @RequestBody MemberDTO member) throws Exception{
		String result = "";
		member.setId(user.getId());
		
		//사용자 정보 변경
		result = myPageService.editInfo(member);
		
		if(result.equals("success")) { //성공한 경우
			//로그인 사용자 정보 변경
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(authentication,user.getId()));
		}
		
		return result;
	}
	
	@ResponseBody
	@PostMapping("/checkPwd")
	public String checkPwd(@AuthenticationPrincipal UserImpl user, @RequestParam("pwd") String pwd) throws Exception {
		
		String result = null;
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		//사용자가 입력한 현재 비밀번호가 로그인 회원 정보와 일치하는지 확인
		if(encoder.matches(pwd, user.getPwd())) {
			result = "pwConfirmSuccess";
		} else {
			result = "pwConfirmFail";
		}
		
		return result;
		
	}
	
	@ResponseBody
	@PostMapping("/changePwd")
	public String changePwd(@AuthenticationPrincipal UserImpl user, @RequestParam("pwd") String pwd) throws Exception {
		MemberDTO member = new MemberDTO();
		member.setId(user.getId());
		member.setPwd(pwd);
		
		//비밀번호 변경
		String result = myPageService.changePwd(member);
		
		if(result.equals("pwConfirmSuccess")) {
			//로그인 사용자 정보 변경
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(authentication,user.getId()));
		}
		
		return result;
		
	}
	
	protected Authentication createNewAuthentication(Authentication currentAuth, String username) {
        UserDetails newPrincipal = memberService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
        newAuth.setDetails(currentAuth.getDetails());
        return newAuth;

    }
}
