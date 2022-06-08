package com.work.workhub.member.message.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
	
	private String url;
	private int no;
	private String receiver;
	private String sender;
	private String name;
	private String depName;

}
