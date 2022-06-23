package com.work.workhub.member.report.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepAttachDTO {
	private int attachmentNo;
	private int repNo;
	private String orgName;
	private String savedName;
	private String savePath;
}
