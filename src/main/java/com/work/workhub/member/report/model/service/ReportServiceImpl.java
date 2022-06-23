package com.work.workhub.member.report.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.workhub.member.report.model.dao.ReportMapper;
import com.work.workhub.member.report.model.dto.ReportDTO;

@Service("reportService")
@Transactional
public class ReportServiceImpl implements ReportService{
	
	private ReportMapper reportMapper;
	
	@Override
	public boolean registReport(ReportDTO report) throws Exception {
		int result = reportMapper.registReport(report);
		
		if(result <= 0) {
			throw new Exception("보고서 등록 실패");
		}
		
		return result > 0? true : false;
	}
	
}
