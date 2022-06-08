package com.work.workhub.member.calendar.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.work.workhub.member.calendar.model.dto.CalendarDTO;
import com.work.workhub.member.calendar.model.dto.CategoryDTO;
import com.work.workhub.member.calendar.model.dto.FullCalendarDTO;
import com.work.workhub.member.calendar.model.service.CalendarService;
import com.work.workhub.member.member.dto.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/calendar")
public class CalendarController {
	
	private CalendarService calendarService;
	private MessageSource messageSource;
	
	@Autowired
	public CalendarController(CalendarService calendarService, MessageSource messageSource) {
		this.calendarService = calendarService;
		this.messageSource = messageSource;
	}
	
//	/* 캘린더 일정 조회 메소드 */
//	@GetMapping("list")
//	public ModelAndView selectCalendarList (ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
//		
//		int no = user.getNo();
//		
//		List<CalendarDTO> calendarList = calendarService.selectCalendarList(no);
//		
//		mv.addObject("calendarList", calendarList);
//		mv.setViewName("calendar/list");
//		
//		return mv;
//	}
	
//	/* 캘린더 일정 */
//	@GetMapping("list/json")
//	@ResponseBody
//	public List<CalendarDTO> selectCalendars(@AuthenticationPrincipal UserImpl user) {
//	      return calendarService.selectCalendarList();
//	}
//	
	
	@GetMapping("list")
	public void calendar() {}
		
	@PostMapping("/list")
	@ResponseBody
	public Map<String, FullCalendarDTO> calendar(@AuthenticationPrincipal UserImpl user) throws JsonProcessingException {
		int no = user.getNo();
		
		Map<String, FullCalendarDTO> calMap = new HashMap<>();
		List<CalendarDTO> calendarList = calendarService.selectCalendarList(no);
		
		log.info("일정목록:{}", calendarList);
		
		for(int i = 0; i < calendarList.size(); i++) {
			CalendarDTO calendar = calendarList.get(i);
			String title = calendar.getCalTitle();
			Date start = calendar.getCalStart();
			Date end = calendar.getCalEnd();
			String allDay = calendar.getCalAlldayStatus();
			
			calMap.put("event" + i, new FullCalendarDTO(title, start, end, allDay));
		}
			
		return calMap;
	}
	
	@GetMapping(value="category", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<CategoryDTO> findCategoryList() {
		return calendarService.findAllCategory();
	}
	
	@GetMapping("regist")
	public void registCalendar() {}
	
	@PostMapping("/regist")
	public String registCalendar(@ModelAttribute CalendarDTO calendar, RedirectAttributes rttr, Locale locale) throws Exception {
		
		log.info("캘린더 등록 메뉴: {}", calendar);
		calendarService.registCalendar(calendar);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("registCalendar", null, locale));
		
		return "redirect:/calendar/list";
	}
	
//	 @DeleteMapping("update")
//	 @ResponseBody
//	 public Map<String, FullCalendarDTO> deleteEvent(@RequestBody List<Map<String, Object>> param, @AuthenticationPrincipal UserImpl user) {
//	 
//		 	Map<String, FullCalendarDTO> calMap = new HashMap<>();
//		 	
//	        for (Map<String, Object> list : param) {
//	        	
//	        	FullCalendarDTO fullcalendar = new FullCalendarDTO();
//	            fullcalender.setTitle((String) list.get("title")); // 이름 받아오기
//	            Date start = (Date) list.get("start");
//	            Date end = (Date) list.get("end");
//	            String allDay = (String) list.get("allDay");
//	            calMap.put("event", new FullCalendarDTO(title, start, end, allDay));
//	            
//	        }
//	        return calMap;
//	 }

}
