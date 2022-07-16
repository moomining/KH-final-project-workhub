package com.work.workhub.member.report.model.dto;

import java.util.Date;

import com.work.workhub.member.member.dto.DepartmentDTO;
import com.work.workhub.member.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
	
	private int repNo;
	private int memberNo;
	private int formNo;
	private int depNo;
	private String title;
	private String content;
	private Date repDate;
	
	private MemberDTO member;
	private RepFormDTO repForm;
	private DepartmentDTO dept;
}
