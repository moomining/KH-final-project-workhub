package com.work.workhub.member.calendar.model.service;

import java.util.List;

import com.work.workhub.member.calendar.model.dto.CalendarDTO;
import com.work.workhub.member.calendar.model.dto.CategoryDTO;

public interface CalendarService {

	List<CalendarDTO> selectCalendarList(int no);
	
	boolean registCalendar(CalendarDTO calendar) throws Exception;

	List<CategoryDTO> findAllCategory();

}
