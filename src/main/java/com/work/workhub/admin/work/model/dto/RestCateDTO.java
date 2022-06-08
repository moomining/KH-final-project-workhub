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
public class RestCateDTO {
	
	private int restNo;
	private String restName;
	private String restDed;
	private String restInclude; 
	private String restMoney;
	private String restPoss;
	private int restDay;
	private String restUse; 
	private String restStatus;
	private Date restDate;
	private Date restModdate;
	private int writeNo;
	private String name;

}
