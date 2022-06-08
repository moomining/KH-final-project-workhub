package com.work.workhub.member.myPage.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.work.workhub.member.member.dto.MemberDTO;

@Mapper
public interface MyPageMapper {

	MemberDTO findMemberById(String username);
	
	int changePwd(MemberDTO member);
	
	int editInfo(MemberDTO member);

}
