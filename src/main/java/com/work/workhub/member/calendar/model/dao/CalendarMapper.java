package com.work.workhub.member.calendar.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.work.workhub.member.calendar.model.dto.CalendarDTO;
import com.work.workhub.member.calendar.model.dto.CategoryDTO;

@Mapper
public interface CalendarMapper {

	List<CalendarDTO> selectCalendarList(int no);

	int registCalendar(CalendarDTO calendar);

	List<CategoryDTO> findAllCategory();

}
