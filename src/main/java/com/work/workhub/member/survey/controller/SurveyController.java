package com.work.workhub.member.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.work.workhub.member.survey.model.dto.SurveyDTO;
import com.work.workhub.member.survey.model.service.SurveyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	private SurveyService surveyService;
	private MessageSource messageSource;
	
	@Autowired
	public SurveyController(SurveyService surveyService, MessageSource messageSource) {
		this.surveyService = surveyService;
		this.messageSource = messageSource;
	}
	
	
	//설문 홈 조회
	@GetMapping("home")
	public ModelAndView surveyHome(ModelAndView mv) {
		
//		List<SurveyDTO> surveyList = surveyService.findSurveyLately();
//		
//		mv.addObject("surveyList", surveyList);
//		mv.setViewName("/survey/home");
		
		return mv;
	}
	
	
	//진행중 설문 목록
	@GetMapping("list_ing")
	public void surveyList() {}

	
	//미참여 설문 상세조회
	@GetMapping("detail")
	public void surveyDetail() {}
	
	
	//참여 설문 상세조회
	@GetMapping("detail_check")
	public void surveyDetailChecked() {}
	
	
	//설문 작성
	@GetMapping("write")
	public void surveyWrite() {}
	
	


}
