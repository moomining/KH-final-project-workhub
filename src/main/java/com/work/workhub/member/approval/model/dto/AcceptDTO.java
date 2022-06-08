package com.work.workhub.member.approval.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcceptDTO {

	private int no;
	private int appNo;
	private int memNo;
	private String status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	private ApprovalDTO app;
}
