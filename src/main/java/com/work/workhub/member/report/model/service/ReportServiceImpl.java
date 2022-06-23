package com.work.workhub.member.report.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.workhub.member.report.model.dto.ReportDTO;

@Service("reportService")
@Transactional
public class ReportServiceImpl implements ReportService{

	@Override
	public int registReport(ReportDTO report) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
