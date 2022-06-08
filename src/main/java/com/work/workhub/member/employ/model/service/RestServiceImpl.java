package com.work.workhub.member.employ.model.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.work.workhub.admin.work.model.dto.RestCateDTO;
import com.work.workhub.admin.work.model.dto.WorkDTO;
import com.work.workhub.member.employ.model.dao.EmpstatusMapper;
import com.work.workhub.member.employ.model.dto.AttDTO;
import com.work.workhub.member.employ.model.dto.ImgDTO;
import com.work.workhub.member.employ.model.dto.RestDTO;


@Service("restService")
@Transactional
public class RestServiceImpl implements RestService{
	
	private final EmpstatusMapper empstatusMapper;
	
	@Autowired
	public RestServiceImpl(EmpstatusMapper empstatusMapper) {
		this.empstatusMapper = empstatusMapper;
	}
	
	/* 근무유형 select box*/
	@Override
	public List<WorkDTO> selectsBox() {
		
		return empstatusMapper.boxsWork();
	}
	
	//휴가 유형 selectBox
	@Override
	public List<RestCateDTO> rcBox() {
		
		return empstatusMapper.boxRc();
	}
	
	//연차신청서 작성
	@Override
	public boolean insertRest(ImgDTO img, RestDTO rest) throws Exception {
		
		//값만 (여기서부터 나눠서 값을 삽입한다.)
		int result = empstatusMapper.insertPara(rest);
		
		//이미지만
		int results = empstatusMapper.insertRest(img);
		

        if(result <= 0 || results <= 0) {
            throw new Exception("연차 신청서 등록 실패");
        }


        return result > 0 ? true : false;
	}
	
	//연차 유형 사용한 것
	@Override
	public List<RestDTO> selectrest() {
		
		return empstatusMapper.restselect();
	}
	
	//연차 유형 사용하지 않은 것
	@Override
	public List<RestDTO> selectrests() {
		
		return empstatusMapper.restsselect();
	}
	
	//연차신청서 파라 옛날 값
	@Override
	public List<RestDTO> paModify(int porestNo) {
		
		return empstatusMapper.modifyPa(porestNo);
	}
	
	//휴가 유형 select box
	@Override
	public List<RestCateDTO> reBox() {
		
		return empstatusMapper.boxRe();
	}
	
	//수정하기 버튼을 눌렀을 때 해당 게시글의 이미지 정보 가져오기
	@Override
	public ImgDTO modifyImgs(int porestNo) {
		
		return empstatusMapper.modifyImgs(porestNo);
	}
	
	//연차 신청서 수정
	@Override
	public boolean modifyRestImg(@RequestParam("img") ImgDTO img,@RequestParam("rest") RestDTO rest) throws Exception {
		
		
		//값만 (여기서부터 나눠서 값을 삽입한다.)
		int result = empstatusMapper.ModifyPara(rest);
		
		//이미지만
		int results = empstatusMapper.ModifyRest(img);
		

        if(result <= 0 || results <= 0) {
            throw new Exception("연차 신청서 등록 실패");
        }


        return result > 0 ? true : false;
		
	}
	
	//삭제하기 버튼을 눌렀을 때 해당 게시글의 이미지 정보 가져오기
	@Override
	public ImgDTO modifyImgss(int porestNo) {
		
		return empstatusMapper.modifyImgss(porestNo);
	}
	
	//연차신청서 삭제
	@Override
	public boolean deleteRest(RestDTO rest, int porestNo) throws Exception {
		
		int result = empstatusMapper.deleteRest(rest, porestNo);
		
		if(result <=0) {
			throw new Exception("연차신청서 삭제 실패");
		}
		
		return result > 0 ? true : false;
	}
	

	

	
	//연차신청서 파라미터  값
//	@Override
//	public boolean insertPara(RestDTO rest) throws Exception {
//		
//		int result = empstatusMapper.insertPara(rest);
//
//        if(result <= 0) {
//            throw new Exception("연차 신청서 데이터 등록 실패");
//        }
//
//
//        return result > 0 ? true : false;
//		
//	}
	

	

}
