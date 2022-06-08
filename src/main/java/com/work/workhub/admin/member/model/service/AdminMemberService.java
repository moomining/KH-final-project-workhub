package com.work.workhub.admin.member.model.service;

import java.util.List;

import com.work.workhub.member.member.dto.DepartmentDTO;
import com.work.workhub.member.member.dto.MemberDTO;

public interface AdminMemberService {

	void signUp(MemberDTO member);
	
	List<MemberDTO> memberList();
	
	List<MemberDTO> memberList(String depNo);
	
	List<DepartmentDTO> depList();
}
