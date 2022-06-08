package com.work.workhub.member.approval.model.dto;

import com.work.workhub.member.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppLineDTO {

	private int no;
	private int appNo;
	private int memNo;
	private int level;
	
	private MemberDTO receiver;
	private ApprovalDTO app;
}
