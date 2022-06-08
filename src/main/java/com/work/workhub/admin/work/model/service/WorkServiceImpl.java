package com.work.workhub.admin.work.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.workhub.admin.work.model.dao.WorkMapper;
import com.work.workhub.admin.work.model.dto.WorkDTO;

@Service("workService")
@Transactional
public class WorkServiceImpl implements WorkService{
	
	private final WorkMapper workMapper;
	
	@Autowired
	public WorkServiceImpl(WorkMapper workMapper) {
		this.workMapper = workMapper;
	}
	
	//근무유형 작성
	@Override
	public boolean workWrite(WorkDTO post) throws Exception {
		
		int result = workMapper.workWrite(post);
		
		if(result <= 0) {
			throw new Exception("게시글 등록에 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
	}
	
	//근무유형 조회
	@Override
	public List<WorkDTO> selectAllwork() {
		
		return workMapper.workSelect();
	}
	
	//근태유형 예전 값
	@Override
	public List<WorkDTO> Modifywork(int workNo) {
		
		return workMapper.Modifywork(workNo);
	}
	
	//근태유형 삭제
	@Override
	public boolean workDelete(WorkDTO post, int workNo) throws Exception {
		
		int result = workMapper.workDelete(post, workNo);
		
		if(result <= 0) {
			throw new Exception("게시글 삭제에 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
		
	}
	
	
	//근태유형 수정
	@Override
	public boolean workModifyGo(WorkDTO post) throws Exception {
		
		int result = workMapper.workModifyGo(post);
		
		if(result <= 0) {
			throw new Exception("게시글 수정에 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
		
	}

	

}
