package com.work.workhub.member.employ.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.workhub.admin.work.model.dto.RestCateDTO;
import com.work.workhub.admin.work.model.dto.WorkDTO;
import com.work.workhub.member.employ.model.dao.EmpstatusMapper;
import com.work.workhub.member.employ.model.dto.AttDTO;


@Service("employmentService")
@Transactional
public class EmploymentServiceImpl implements EmploymentService{
	
	private final EmpstatusMapper empstatusMapper;
	
	@Autowired
	public EmploymentServiceImpl(EmpstatusMapper empstatusMapper) {
		this.empstatusMapper = empstatusMapper;
	}
	
	//출근하기 버튼 눌렀을 때
	@Override
	public boolean employmentIn(AttDTO post) throws Exception {
        
		int result = empstatusMapper.employmentIn(post);
		
		if(result <= 0) {
			throw new Exception("출근 등록을 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
	}
	
	//퇴근하기 버튼 눌렀을 때
	@Override
	public boolean employmentOut(AttDTO post) throws Exception {
		
		int result = empstatusMapper.employmentOut(post);
		
		if(result <= 0) {
			throw new Exception("퇴근 등록을 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
	}
	
	/* 근무유형 select box*/	
	@Override
	public List<WorkDTO> selectBox() {
		
		return empstatusMapper.boxWork();
	}
	
	//select box 근무 등록
	@Override
	public boolean employmentOther(AttDTO post) throws Exception {
		
		int result = empstatusMapper.employmentOther(post);
		
		if(result <= 0) {
			throw new Exception("근태 등록을 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
	}

	@Override
	public List<AttDTO> attenSelect() {
		
		return empstatusMapper.attenSelect();
	}
	
	//내 근태 조회
	@Override
	public List<AttDTO> selectMy() {
		
		return empstatusMapper.mySelect();
	}
	
	//내 근태 예전 값
	@Override
	public List<AttDTO> beforedData(int attNo) {
		
		return empstatusMapper.beforedData(attNo);
	}
	
	//근태 유형 select box
	@Override
	public List<RestCateDTO> sBox() {
		
		return empstatusMapper.sBox();
	}
	
	//근태관리 수정
	@Override
	public boolean employmentModifyGo(AttDTO post) throws Exception {
		
		int result = empstatusMapper.employmentModifyGo(post);
		
		if(result <= 0) {
			throw new Exception("근태 수정을 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
		
	}
	
	//근태관리 삭제
	@Override
	public boolean employDelete(AttDTO post, int attNo) throws Exception {
		
		int result = empstatusMapper.employDelete(post, attNo);
		
		if(result <= 0) {
			throw new Exception("내 근태 삭제를 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
	}


	
	//근태 엑셀
	@Override
	public List<AttDTO> excelDown() throws Exception {
		
		return empstatusMapper.excelDown();
	}
	

	
	
	



}
