package com.work.workhub.member.employ.model.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.work.workhub.admin.work.model.dto.RestCateDTO;
import com.work.workhub.admin.work.model.dto.WorkDTO;
import com.work.workhub.member.employ.model.dto.ImgDTO;
import com.work.workhub.member.employ.model.dto.RestDTO;



public interface RestService {
	
	/* 근무유형 select box*/	
	List<WorkDTO> selectsBox();
	
	//휴가 유형 selectBox
	List<RestCateDTO> rcBox();
	
	//연차신청서 작성 이미지
	boolean insertRest(ImgDTO img, RestDTO rest) throws Exception;
	
	//연차 유형 사용한 것
	List<RestDTO> selectrest();
	
	//연차 유형 사용하지 않은 것
	List<RestDTO> selectrests();
	
	//연차신청서 파라 옛날
	List<RestDTO> paModify(int porestNo);
	
	//휴가 유형 select box
	List<RestCateDTO> reBox();
	
	//수정하기 버튼을 눌렀을 때 해당 게시글의 이미지 정보 가져오기
	ImgDTO modifyImgs(int porestNo);
	
	//연차 신청서 수정
	boolean modifyRestImg(@RequestParam("img") ImgDTO img, @RequestParam("rest") RestDTO rest) throws Exception;
	
	//삭제 버튼을 눌렀을 때 해당 게시글의 이미지 정보 가져오기
	ImgDTO modifyImgss(int porestNo);
	
	//연차신청서 삭제
	boolean deleteRest(RestDTO rest, int porestNo) throws Exception;
	
	
	

	//연차신청서 파라미터  값
//	boolean insertPara(RestDTO rest) throws Exception;
	

}
