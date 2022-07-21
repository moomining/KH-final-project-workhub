package com.work.workhub.member.report.model.util;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.work.workhub.member.report.exception.AttachFileException;
import com.work.workhub.member.report.model.dto.RepAttachDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUtils {
	private String uploadFilePath;
	
	@Autowired
	public FileUtils(@Value("${custom.path.upload-files}")String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	
	public List<RepAttachDTO> uploadFiles(MultipartFile[] files, int repNo) {
						
			if(files[0].getSize() < 1) {
				return Collections.emptyList();
			}
				
			log.info("넘어온 file : {}", files[0]);
			
			
			List<RepAttachDTO> attachList = new ArrayList<>();
			
			// file 저장 경로 설정
			File mkdir = new File(uploadFilePath);
			if (!mkdir.exists()) {
				mkdir.mkdirs();
			}
	
			// 파일 개수만큼 forEach 실행
			for(MultipartFile file : files) {
				
				try {
					// 파일 확장자
					final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
					// 서버에 저장할 파일명 (랜덤 문자열 + 확장자) 
					final String savedName = UUID.randomUUID().toString().replace("-", "") + "." + extension;
					
					String orgName = file.getOriginalFilename();
					
					//업로드 경로에 savedName과 동일한 이름을 가진 파일 생성
					File target = new File(uploadFilePath, savedName);
					file.transferTo(target);
					
		
					//file에 관한 정보 추출 후 보관
					RepAttachDTO attach = new RepAttachDTO();
					attach.setOrgName(orgName);
					attach.setSavedName(savedName);
					attach.setSavePath(uploadFilePath);
					
					attachList.add(attach);
					
					
				} catch (IOException e) {
					throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
				} catch (Exception e) {
					throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
				}
				
			} // end of for
			
	
			
			
			return attachList;
	}
	
	
}
