package com.work.workhub.member.reserve.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.member.reserve.model.dto.ResMeetingDTO;
import com.work.workhub.member.reserve.model.service.MeetingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/reserve/meeting")
public class MeetingModifyController {
	
	private MeetingService meetingService;
	private MessageSource messageSource;
	
	@Autowired
	public MeetingModifyController(MeetingService meetingService, MessageSource messageSource) {
		this.meetingService = meetingService;
		this.messageSource = messageSource;
	}
	
	/* 회의실 예약 수정용 화면 이동*/
	@GetMapping("modify")
	public void modifyMeeting() {
		
	}
	
	/* 앞에 RequestMapping /reserve/meeting 있기 떄문에 modfiyInfo 똑같은 GetMapping name 써줘도
	 * 문제 없다.*/
	@GetMapping("modifyInfo")
	public String modifyMeeting(Model model, @RequestParam Map<String,String> meetingInfo) throws ParseException {
		
		String resNo = meetingInfo.get("modifyResNo");
		log.info(resNo);
		String roomName = meetingInfo.get("modifyRoomName");
		log.info(roomName);
		String startTime = meetingInfo.get("modifyStartTime");
		log.info(startTime);
		String endTime = meetingInfo.get("modifyEndTime");
		log.info(endTime);
		String name = meetingInfo.get("modifyName");
		String meetPurpose = meetingInfo.get("modifyMeetPurpose");
		
        String startTime2 = null;
        String endTime2 = null;
        
        SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
 
        // 여기에 원하는 포맷을 넣어주면 된다
        SimpleDateFormat tranSimpleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);

        try {
            Date data = recvSimpleFormat.parse(startTime);
            startTime2 = tranSimpleFormat.format(data);
            
            Date data2 = recvSimpleFormat.parse(endTime);
            endTime2 = tranSimpleFormat.format(data2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        log.info(startTime2);
		log.info(endTime2);
        
		Map<String, Object> meetingInfo2 = new HashMap<String, Object>();
		meetingInfo2.put("resNo", resNo);
		meetingInfo2.put("roomName", roomName);
		meetingInfo2.put("startTime", startTime2);
		meetingInfo2.put("endTime", endTime2);
		meetingInfo2.put("name", name);
		meetingInfo2.put("meetPurpose", meetPurpose);	
		
//		model.addAllAttributes(meetingInfo);
		
		model.addAttribute("meetingInfo", meetingInfo);
		model.addAttribute("meetingInfo2", meetingInfo2);
		
		log.info("meetingInfo : {} ", meetingInfo);
		log.info("meetingInfo2 : {} ", meetingInfo2);
		return "/reserve/meeting/modify";
		
	}
	
	/* 회의실 예약 수정 메소드 */
	@PostMapping("modify")
	public String modifyMeeting(@ModelAttribute ResMeetingDTO meeting, RedirectAttributes rttr, Locale locale) throws Exception {
		
		log.info("수정할 회의실 정보 : {}",meeting);
		
		meetingService.modifyMeetingRes(meeting);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("modifyMeetingRes", null, locale));
		
		return "redirect:/reserve/mylist";
		
	}
	
	/* 회의실 예약 삭제 메소드 */
	@PostMapping("delete")
	public String updateMeetingStatus(@ModelAttribute ResMeetingDTO meeting, RedirectAttributes rttr, Locale locale) throws Exception {
		
		log.info("삭제할 예약 정보 : {}",meeting);
		
		meetingService.updateMeetingStatus(meeting);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("deleteMeeting", null, locale));
		
		return "redirect:/reserve/mylist";
		
	}
	
}
