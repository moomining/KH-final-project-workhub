package com.work.workhub.member.calendar.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.workhub.member.calendar.model.dao.CalendarMapper;
import com.work.workhub.member.calendar.model.dto.CalendarDTO;
import com.work.workhub.member.calendar.model.dto.CategoryDTO;


@Service
@Transactional
public class CalendarServiceImpl implements CalendarService {
	
	private CalendarMapper calendarMapper;
	
	@Autowired
	public CalendarServiceImpl (CalendarMapper calendarMapper) {
		this.calendarMapper = calendarMapper;
	}

	@Override
	public List<CalendarDTO> selectCalendarList(int no) {
		return calendarMapper.selectCalendarList(no);
	}

	@Override
	public boolean registCalendar(CalendarDTO calendar) throws Exception {
		
		int result = calendarMapper.registCalendar(calendar);
		
		if(result <= 0) {
			throw new Exception("일정 등록에 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
	}

	@Override
	public List<CategoryDTO> findAllCategory() {
		return calendarMapper.findAllCategory();
	}

}
