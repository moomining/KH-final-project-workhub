package com.work.workhub.member.survey.model.dto;

import lombok.Data;

@Data
public class AnswerDTO {
	
	private int ansNo;			//항목 아이디
	private int queNo;			//질문 아이디(PK)
	private int survNo;			//설문 아이디(PK)
	private String ansContent;	//항목 내용

	private QuestionDTO question;
	private SurveyDTO survey;
}
