package com.work.workhub.member.survey.model.dto;

import java.sql.Date;

import com.work.workhub.member.member.dto.DepartmentDTO;
import com.work.workhub.member.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class SurveyDTO {
	
	private int survNo;
	private String survTitle;
	private Date survDate;
	private Date survEndDate;
	private String survContent;
	private int survSum;
	private int survNum;
	private int no;
	private int depNo;
	
	
	private MemberDTO member;
	private DepartmentDTO dept;
}
