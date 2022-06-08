package com.work.workhub.member.reserve.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
	private MessageSource messageSource;

	@Autowired
	public SelectMyReserveList(CarResService carResService, MessageSource messageSource, MeetingService meetingService) {
		this.carResService = carResService;
		this.meetingService = meetingService;
		this.messageSource = messageSource;
	}
	
	@GetMapping("mylist")
	public ModelAndView selectMyReserveList(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		
		log.info("로그인 유저 정보 : {}", user);

		List<ResCarDTO> resCarList = carResService.selectAllResCarList();
		
		mv.addObject("resCarList", resCarList);
		log.info("resCarList : {}", resCarList);
		
		
		List<ResMeetingDTO> resMeetingList = meetingService.selectAllResMeeting();
		
		mv.addObject("resMeetingList", resMeetingList);
		
		log.info("resMeetingList : {}", resMeetingList);
		
		mv.setViewName("reserve/mylist");
		
		return mv;
	}

}
