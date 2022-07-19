package com.work.workhub.member.report.model.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.work.workhub.member.report.model.dto.ReportDTO;

public interface ReportService {

	public boolean registReport(ReportDTO report) throws Exception;
	
	public boolean registReport(ReportDTO report, MultipartFile[] files) throws Exception;
	
	public List<ReportDTO> selectMyList(int no);

}
