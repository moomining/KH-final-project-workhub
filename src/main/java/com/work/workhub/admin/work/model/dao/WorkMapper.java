package com.work.workhub.admin.work.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.work.workhub.admin.work.model.dto.MemberGroupDTO;
import com.work.workhub.admin.work.model.dto.RestCateDTO;
import com.work.workhub.admin.work.model.dto.WorkDTO;
import com.work.workhub.admin.work.model.dto.WorkGroupDTO;
import com.work.workhub.member.member.dto.MemberDTO;

@Mapper
public interface WorkMapper {

	//근태유형 작성
	int workWrite(WorkDTO post);

	//근태유형 조회
	List<WorkDTO> workSelect();
	
	//연차유형 조회
	List<RestCateDTO> restcateSelect();
	
	//연차유형 작성
	int restCateWrite(RestCateDTO post);
	
	//근무그룹 조회
	List<WorkGroupDTO> workGroupSelect();
	
	//근무그룹 작성
	int workGroupWrite(WorkGroupDTO post);
	
	/* 근무그룹 select box*/
	List<WorkGroupDTO> boxSelect();
	
	/*사원 근무그룹 등록*/
	int memberGroupWrite(MemberGroupDTO post);
	
	//사원 아이디 검색으로 근무그룹 조회
	List<MemberDTO> memberGroupSelect(int no);
	
	//근태 유형 수정 예전 값
	List<WorkDTO> Modifywork(int workNo);
	
	//근태유형 삭제
	int workDelete(WorkDTO post, int workNo);
	
	
	//근태유형 수정
	int workModifyGo(WorkDTO post);
	
	//사원근무그룹 수정 예전 값 보기
	List<WorkDTO> ModifymemberGroup(int gmNo);
	
	//사원 근무그룹 수정
	int memberGroupModifyGo(MemberGroupDTO post);
	
	//사원 근무그룹 삭제
	int memberGroupDelete(MemberGroupDTO post, int gmNo);
	
	//연차 유형 예전 값
	List<RestCateDTO> ModifyrestCate(int restNo);
	
	//연차 유형 수정
	int restCateModifyGo(RestCateDTO post);
	
	//연차 유형 삭제
	int restCateDelete(RestCateDTO post, int restNo);
	
	//근무그룹 예전값
	List<WorkGroupDTO> ModifyworkGroup(int groupNo);
	
	//근무 그룹 수정
	int workGroupModifyGo(WorkGroupDTO post);
	
	//근무그룹 삭제
	int workGroupDelete(WorkGroupDTO post, int groupNo);
	
	//근무그룹조회 use='사용'
//	List<WorkGroupDTO> workGroupSelects();


	

}
