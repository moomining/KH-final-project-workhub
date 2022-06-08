package com.work.workhub.member.approval.model.dto;

import com.work.workhub.member.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceDTO {
	
	private int no;
	private int appNo;
	private int memNo;
	
	private MemberDTO ref;
	private ApprovalDTO app;
	
}
