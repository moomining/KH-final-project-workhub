package com.work.workhub.admin.work.model.service;

import java.util.List;

import com.work.workhub.admin.work.model.dto.WorkDTO;

public interface WorkService {
	
	//근태유형 삽입
	boolean workWrite(WorkDTO post) throws Exception;
	
	//근태유형 조회
	List<WorkDTO> selectAllwork();
	
	//근태유형 수정 예전 값
	List<WorkDTO> Modifywork(int workNo);
	
	//근태유형 삭제
	boolean workDelete(WorkDTO post, int workNo) throws Exception;
	
	//근태유형 수정
	boolean workModifyGo(WorkDTO post) throws Exception;


	
	

}
