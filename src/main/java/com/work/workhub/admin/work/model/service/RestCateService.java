package com.work.workhub.admin.work.model.service;

import java.util.List;

import com.work.workhub.admin.work.model.dto.RestCateDTO;


public interface RestCateService {
	
	//연차유형 조회
	List<RestCateDTO> selectAllrestcate();
	
	//연차유형 작성
	boolean restCateWrite(RestCateDTO post) throws Exception;
	
	//연차유형 예전 값 
	List<RestCateDTO> ModifyrestCate(int restNo);
	
	//연차유형 수정
	boolean restCateModifyGo(RestCateDTO post) throws Exception;
	
	//연차유형 삭제
	boolean restCateDelete(RestCateDTO post, int restNo) throws Exception;


	

}
