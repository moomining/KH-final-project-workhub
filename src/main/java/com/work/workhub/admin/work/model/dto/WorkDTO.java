package com.work.workhub.admin.work.model.dto;

import java.sql.Date;

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
public class WorkDTO {
	
	private int workNo;
	private String workName;
	private String workInclude;
	private String workUse; 
	private String workStatus;
	private Date workDate;
	private Date workModdate;
	private int writeNo;
	private String name;

}
