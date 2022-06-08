package com.work.workhub.member.survey.model.dto;

import com.work.workhub.member.member.dto.MemberDTO;

import lombok.Data;

@Data
public class ResponseDTO {
	
	private int no;
	private int ansNo;
	private int queNo;
	private int survNo;
	
	private MemberDTO member;
	private AnswerDTO answer;
	private QuestionDTO question;
	private SurveyDTO survey;
}
