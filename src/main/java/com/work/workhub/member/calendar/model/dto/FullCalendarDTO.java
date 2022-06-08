package com.work.workhub.member.calendar.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullCalendarDTO {
	
	private String title;
	private Date start;
	private Date end;
	private String allDay;

}
