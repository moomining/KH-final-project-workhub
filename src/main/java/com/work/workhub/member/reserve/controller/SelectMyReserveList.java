package com.work.workhub.member.reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.work.workhub.member.member.dto.UserImpl;
import com.work.workhub.member.reserve.model.dto.ResCarDTO;
import com.work.workhub.member.reserve.model.dto.ResMeetingDTO;
import com.work.workhub.member.reserve.model.service.CarResService;
import com.work.workhub.member.reserve.model.service.MeetingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("reserve")
public class SelectMyReserveList {
	
	private CarResService carResService;
	private MeetingService meetingService;

	@Autowired
	public SelectMyReserveList(CarResService carResService, MeetingService meetingService) {
		this.carResService = carResService;
		this.meetingService = meetingService;
	}
	
	@GetMapping("mylist")
	public ModelAndView selectMyReserveList(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		
		log.info("로그인 유저 정보 : {}", user);
		int no = user.getNo();

		List<ResCarDTO> resCarList = carResService.selectMyResCarList(no);
		
		mv.addObject("resCarList", resCarList);
		log.info("resCarList : {}", resCarList);
		
		
		List<ResMeetingDTO> resMeetingList = meetingService.selectMyResMeeting(no);
		
		mv.addObject("resMeetingList", resMeetingList);
		
		log.info("resMeetingList : {}", resMeetingList);
		
		mv.setViewName("reserve/mylist");
		
		return mv;
	}

}
