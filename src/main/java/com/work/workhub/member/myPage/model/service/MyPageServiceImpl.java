package com.work.workhub.member.myPage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.work.workhub.member.member.dto.MemberDTO;
import com.work.workhub.member.myPage.model.dao.MyPageMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private MyPageMapper myPageMapper;
	
	@Override
	public MemberDTO findMemberById(String username) {
		return myPageMapper.findMemberById(username);
	}

	@Override
	public String changePwd(MemberDTO member) {
		String result = "";
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			member.setPwd(encoder.encode(member.getPwd()));
			
			if(myPageMapper.changePwd(member) != 0) {
				result = "pwConfirmSuccess";
			}else {
				result = "pwConfirmFail";
			}
			
		}catch(Exception e) {
			result = "pwConfirmFail";
		}
		return result;
	}

	@Override
	public String editInfo(MemberDTO member) {
		String result = "";
		
		if(myPageMapper.editInfo(member) != 0) {
			result = "success";
		}else {
			result = "fail";
		}
		
		return result;
	}
}
