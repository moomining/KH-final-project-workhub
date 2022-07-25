package com.work.workhub.member.reserve.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.admin.reserve.model.dto.MeetingRoomDTO;
import com.work.workhub.member.member.dto.UserImpl;
import com.work.workhub.member.reserve.model.dto.ResMeetingDTO;
import com.work.workhub.member.reserve.model.service.MeetingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("reserve/meeting")
public class MeetingSelectController {
	
	private MeetingService meetingService;
	private MessageSource messageSource;
	
	@Autowired
	public MeetingSelectController(MeetingService meetingService, MessageSource messageSource) {
		this.meetingService = meetingService;
		this.messageSource = messageSource;
	}
	
	@GetMapping("list")
	public ModelAndView selectMeetingList(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		/* time과 DTO를 담아줄 hashMap 만들기 */
		HashMap<String, ResMeetingDTO> timeMap = new LinkedHashMap<>();
		timeMap.put("08:00", null); 	timeMap.put("08:30", null);
		timeMap.put("09:00", null); 	timeMap.put("09:30", null);
		timeMap.put("10:00", null); 	timeMap.put("10:30", null);
		timeMap.put("11:00", null);		timeMap.put("11:30", null);
		timeMap.put("12:00", null);		timeMap.put("12:30", null);
		timeMap.put("13:00", null);		timeMap.put("13:30", null);
		timeMap.put("14:00", null);		timeMap.put("14:30", null);
		timeMap.put("15:00", null);		timeMap.put("15:30", null);
		timeMap.put("16:00", null);		timeMap.put("16:30", null);
		timeMap.put("17:00", null);		timeMap.put("17:30", null);
		timeMap.put("18:00", null);		timeMap.put("18:30", null);
		timeMap.put("19:00", null);		timeMap.put("19:30", null);
		timeMap.put("20:00", null);		timeMap.put("20:30", null);
		timeMap.put("21:00", null);		timeMap.put("21:30", null);
		timeMap.put("22:00", null);		timeMap.put("22:30", null);
		timeMap.put("23:00", null);		timeMap.put("23:30", null);
		
		log.info("timeMap 생성한 것 : {}" , timeMap);
		
		
		/* 상단 location 분류 바에 따라 회의실 목록 불러오기 */
		/*
		 * log.info("선택된 locationNo : {}", locationNo); MeetingRoomDTO room =
		 * meetingService.selectByLocationNo(locationNo);
		 */
		
		/* 미팅룸 장소, 미팅룸 리스트 불러오기 */
		List<MeetingRoomDTO> locationList = meetingService.selectAllLocation();
		List<MeetingRoomDTO> roomList = meetingService.selectRoomList();
		
		mv.addObject("locationList", locationList);
		mv.addObject("roomList", roomList);

		/* 미팅룸 예약 리스트 불러오기 */
		List<ResMeetingDTO> resMeetingList = meetingService.selectAllResMeeting();
		
		mv.addObject("resMeetingList", resMeetingList);
		
		for(ResMeetingDTO meetingList : resMeetingList) {
			Date endTime = meetingList.getStartTime();
			
			String endTime2 = endTime.toString();
			log.info("날짜 String으로 변환한 것 : {}", endTime2);
			
			String timeExtract = endTime2.substring(11, 16);
			log.info("시간 추출 : {}", timeExtract);
			
			for(Entry<String, ResMeetingDTO> entry : timeMap.entrySet()) {
				if(entry.getKey().equals(timeExtract)) {
					entry.setValue(meetingList);
				}
			}
						
		}
		
		log.info("예약 정보 있는 거 반영한 timeMap : {}", timeMap);
		
		// 시간별 예약 정보가 있는 hashMap
		mv.addObject("timeMap", timeMap);
		
		mv.setViewName("reserve/meeting/list");
		
		return mv;
	}
	
	@PostMapping("list")
	public String reserveRoom(@ModelAttribute ResMeetingDTO resRoom, RedirectAttributes rttr, Locale locale, HttpServletRequest rq) throws Exception {
		log.info("등록 요청 : {} ", resRoom);
		
		meetingService.reserveRoom(resRoom);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("registRoomReservation", null, locale));
		
		String referer = rq.getHeader("Referer");
		
		return "redirect:"+referer;
	}
	
	@GetMapping("date")
	public ModelAndView selectMeetingListByDate(@RequestParam String resDate, ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		/* time과 DTO를 담아줄 hashMap 만들기 */
		HashMap<String, ResMeetingDTO> timeMap = new LinkedHashMap<>();
		timeMap.put("08:00", null); 	timeMap.put("08:30", null);
		timeMap.put("09:00", null); 	timeMap.put("09:30", null);
		timeMap.put("10:00", null); 	timeMap.put("10:30", null);
		timeMap.put("11:00", null);		timeMap.put("11:30", null);
		timeMap.put("12:00", null);		timeMap.put("12:30", null);
		timeMap.put("13:00", null);		timeMap.put("13:30", null);
		timeMap.put("14:00", null);		timeMap.put("14:30", null);
		timeMap.put("15:00", null);		timeMap.put("15:30", null);
		timeMap.put("16:00", null);		timeMap.put("16:30", null);
		timeMap.put("17:00", null);		timeMap.put("17:30", null);
		timeMap.put("18:00", null);		timeMap.put("18:30", null);
		timeMap.put("19:00", null);		timeMap.put("19:30", null);
		timeMap.put("20:00", null);		timeMap.put("20:30", null);
		timeMap.put("21:00", null);		timeMap.put("21:30", null);
		timeMap.put("22:00", null);		timeMap.put("22:30", null);
		timeMap.put("23:00", null);		timeMap.put("23:30", null);
		
		log.info("timeMap 생성한 것 : {}" , timeMap);
		
		
		List<MeetingRoomDTO> locationList = meetingService.selectAllLocation();
		List<MeetingRoomDTO> roomList = meetingService.selectRoomList();
		
		mv.addObject("locationList", locationList);
		mv.addObject("roomList", roomList);
		mv.addObject("resDate", resDate);
		
		log.info("resDate : {}", resDate);
		
		List<ResMeetingDTO> resList = meetingService.dateSearch(resDate);
		
		log.info("그날의 예약 리스트 : {}", resList);
		
		mv.addObject("resList", resList);
		
		for(ResMeetingDTO meetingList : resList) {
			Date endTime = meetingList.getStartTime();
			
			String endTime2 = endTime.toString();
			log.info("날짜 String으로 변환한 것 : {}", endTime2);
			
			String timeExtract = endTime2.substring(11, 16);
			log.info("시간 추출 : {}", timeExtract);
			
			for(Entry<String, ResMeetingDTO> entry : timeMap.entrySet()) {
				if(entry.getKey().equals(timeExtract)) {
					entry.setValue(meetingList);
				}
			}
						
		}
		
		log.info("예약 정보 있는 거 반영한 timeMap : {}", timeMap);
		
		// 시간별 예약 정보가 있는 hashMap
		mv.addObject("timeMap", timeMap);
		
		mv.setViewName("reserve/meeting/date");
		
		return mv;
		
	}
	
	
	
	
}
