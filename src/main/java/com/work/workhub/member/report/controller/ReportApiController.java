package com.work.workhub.member.report.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.work.workhub.exception.CustomException;
import com.work.workhub.exception.ErrorCode;

@RestController
@RequestMapping("/api")
public class ReportApiController {
	
	@GetMapping("/test")
	public String test() {
		throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
	}
}
