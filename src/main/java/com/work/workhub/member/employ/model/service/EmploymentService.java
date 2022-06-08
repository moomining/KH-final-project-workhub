package com.work.workhub.member.employ.model.service;

import java.util.List;

import com.work.workhub.admin.work.model.dto.RestCateDTO;
import com.work.workhub.admin.work.model.dto.WorkDTO;
import com.work.workhub.member.employ.model.dto.AttDTO;


public interface EmploymentService {
	
	//출근하기 버튼을 눌렀을 때
	boolean employmentIn(AttDTO post) throws Exception;
	
	//퇴근하기 버튼을 눌렀을 때
	boolean employmentOut(AttDTO post) throws Exception;
	
	/* 근무유형 select box*/	
	List<WorkDTO> selectBox();
	
	//select box 근무 등록
	boolean employmentOther(AttDTO post) throws Exception;
	
	//근태 조회
	List<AttDTO> attenSelect();
	
	//근태 조회
	List<AttDTO> selectMy();
	
	//내 근태 예전 값
	List<AttDTO> beforedData(int attNo);
	
	//근태 유형 select box
	List<RestCateDTO> sBox();
	
	//근태관리 수정
	boolean employmentModifyGo(AttDTO post) throws Exception;

	
	
	//근태관리 삭제
	boolean employDelete(AttDTO post, int attNo) throws Exception;
	
	//근태 엑셀
	List<AttDTO> excelDown() throws Exception;
	

	
	
	
	
	

	

	

}
