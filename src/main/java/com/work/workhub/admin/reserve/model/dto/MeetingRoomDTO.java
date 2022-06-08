package com.work.workhub.admin.reserve.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRoomDTO {
	
	private int roomNo;
	private String rmLocation;
	private String roomName;
	private String roomYn;
	private String delYn;
	private String orgName;
	private String savedName;
	private String savePath;
	

}
