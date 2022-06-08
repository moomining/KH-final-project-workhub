package com.work.workhub.admin.work.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.workhub.admin.work.model.dao.WorkMapper;
import com.work.workhub.admin.work.model.dto.MemberGroupDTO;
import com.work.workhub.admin.work.model.dto.WorkDTO;
import com.work.workhub.admin.work.model.dto.WorkGroupDTO;
import com.work.workhub.member.member.dto.MemberDTO;




@Service("membergroupService")
@Transactional
public class MemberGroupServiceImpl implements MemberGroupService{
	
	private final WorkMapper workMapper;
	
	@Autowired
	public MemberGroupServiceImpl(WorkMapper workMapper) {
		this.workMapper = workMapper;
	}
	
	/* 근무그룹 select box*/
	@Override
	public List<WorkGroupDTO> selectAllbox() {
		
		return workMapper.boxSelect();
	}
	
	
	/*사원 근무그룹 등록*/
	@Override
	public boolean memberGroupWrite(MemberGroupDTO post) throws Exception {
		
		int result = workMapper.memberGroupWrite(post);
		
		if(result <= 0) {
			throw new Exception("게시글 등록에 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
	}
	
	//사원 아이디 검색으로 근무그룹 조회
	@Override
	public List<MemberDTO> selectAllmemberGroup(int no) {
		
		return workMapper.memberGroupSelect(no);
	}
	
	 //사원근무그룹 수정 예전 값 보기
	@Override
	public List<WorkDTO> ModifymemberGroup(int gmNo) {
		
		return workMapper.ModifymemberGroup(gmNo);
	}
	
	//사원 근무그룹 수정
	@Override
	public boolean memberGroupModifyGo(MemberGroupDTO post) throws Exception {
		
		int result = workMapper.memberGroupModifyGo(post);
		
		if(result <= 0) {
			throw new Exception("게시글 수정에 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
		
	}
	
	//사원근무그룹 삭제
	@Override
	public boolean memberGroupDelete(MemberGroupDTO post, int gmNo) throws Exception {
		
		int result = workMapper.memberGroupDelete(post, gmNo);
		
		if(result <= 0) {
			throw new Exception("게시글 삭제에 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
		
	}
	




}
