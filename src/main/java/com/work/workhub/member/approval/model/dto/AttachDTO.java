package com.work.workhub.member.approval.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachDTO {
	
	private int no;
	private int appNo;
	private String origin;
	private String edit;
	private String root;
}
