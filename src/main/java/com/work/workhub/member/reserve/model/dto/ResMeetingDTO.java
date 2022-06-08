package com.work.workhub.member.reserve.model.dto;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.work.workhub.admin.reserve.model.dto.MeetingRoomDTO;
import com.work.workhub.member.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResMeetingDTO {
	private int resNo;
	private int memberNo;
	private int roomNo;
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date startTime;
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private Date endTime;
	private String meetPurpose;
	private String delYn;
	
	private MemberDTO member;
	private MeetingRoomDTO room;
	
}
