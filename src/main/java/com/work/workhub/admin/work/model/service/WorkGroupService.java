package com.work.workhub.admin.work.model.service;

import java.util.List;

import com.work.workhub.admin.work.model.dto.WorkGroupDTO;




public interface WorkGroupService {
	
	//근무그룹 조회
	List<WorkGroupDTO> selectAllworkGroup();
	
	//근무그룹 작성
	boolean workGroupWrite(WorkGroupDTO post) throws Exception;
	
	//근무그룹 예전 값
	List<WorkGroupDTO> ModifyworkGroup(int groupNo);
	
	//근무그룹 수정
	boolean workGroupModifyGo(WorkGroupDTO post) throws Exception;
	
	//근무그룹 삭제 
	boolean workGroupDelete(WorkGroupDTO post, int groupNo) throws Exception;
	
	//근무그룹 조회 use='사용' 만
//	List<WorkGroupDTO> selectAllworkGroups();
	
	

	
	

}
