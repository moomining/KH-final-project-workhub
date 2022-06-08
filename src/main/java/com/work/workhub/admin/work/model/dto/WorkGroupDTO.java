package com.work.workhub.admin.work.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class WorkGroupDTO {
	
	private int groupNo;
	private String groupName;
	private String groupEx;
	
	
	private String groupDay; 
	@DateTimeFormat(pattern = "HH mm")
	private String groupFirdate;
	@DateTimeFormat(pattern = "HH:mm")
	private String groupEnddate;
	@DateTimeFormat(pattern = "HH:mm")
	private String groupFirrest;
	@DateTimeFormat(pattern = "HH:mm")
	private String groupEndrest; 
	private String groupStatus;
	private int groupRest;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String groupDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String groupModdate;
	private String groupUse;
	private int writeNo;
	private String name;

}
