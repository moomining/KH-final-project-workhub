package com.work.workhub.member.approval.model.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.work.workhub.member.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDTO {

	private int no;
	private int memNo;
	private String title;
	private String content;
	private String status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;
	
	//결재자
	private String receiver;
	//참조자
	private String ref;
	
	private MemberDTO sender;
	private AttachDTO att;
}
