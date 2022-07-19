package com.work.workhub.member.report.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.work.workhub.member.report.model.dao.ReportMapper;
import com.work.workhub.member.report.model.dto.RepAttachDTO;
import com.work.workhub.member.report.model.dto.ReportDTO;
import com.work.workhub.member.report.model.util.FileUtils;

@Service("reportService")
@Transactional
public class ReportServiceImpl implements ReportService{

	private ReportMapper reportMapper;
	private FileUtils fileUtils;
	
	@Autowired
	public ReportServiceImpl(ReportMapper reportMapper) {
		this.reportMapper = reportMapper;
	}
	
	@Override
	public boolean registReport(ReportDTO report) throws Exception {
		int result = reportMapper.registReport(report);

		if(result <= 0) {
			throw new Exception("보고서 등록 실패");
		}

		return result > 0? true : false;
	}
	
	@Override
	public boolean registReport(ReportDTO report, MultipartFile[] files) throws Exception {
		int queryResult = 1;
		
		if(registReport(report) == false) {
			return false;
		}
		
		List<RepAttachDTO> fileList = fileUtils.uploadFiles(files, report.getRepNo());
		
		if(CollectionUtils.isEmpty(fileList) == false) {
			queryResult = reportMapper.insertAttach(fileList);
			if(queryResult < 1) {
				queryResult = 0;
			}
		}
		
		return (queryResult > 0);
	}

	@Override
	public List<ReportDTO> selectMyList(int no) {
		return reportMapper.selectMyList(no);
	}

	


}
