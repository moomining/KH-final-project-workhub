package com.work.workhub.member.report.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
	
	private int repNo;
	private int no;
	private int formNo;
	private int depNo;
	private String title;
	private String content;
	
	
}
