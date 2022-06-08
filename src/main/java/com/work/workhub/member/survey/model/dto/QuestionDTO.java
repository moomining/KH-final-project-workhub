package com.work.workhub.member.survey.model.dto;

import lombok.Data;

@Data
public class QuestionDTO {
	
	private int queNo;			//질문 아이디
	private int survNo;			//설문 아이디(PK)
	private String queTitle;	//설문 제목
	private int queNum;			//선택가능 문항수
	
	private SurveyDTO survey;
}
