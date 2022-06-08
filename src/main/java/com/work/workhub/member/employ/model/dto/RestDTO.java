package com.work.workhub.member.employ.model.dto;

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
public class RestDTO {
		
	//연차 신청
	   private int porestNo;
	   private int no;
	   private int restNo;
	   private int repNo;
	   private Date porestDate;
	   private Date porestModdate;
	   private Date porestFir;
	   private Date porestEnd;
	   private String porestReason;
	   private String porestStatus;
	   private int porestDay;
	   private String porestYn;
	   private String porestOk;
	   private Date porestPoss;
	   
	   private String name;
	   private String depName;
	   private String restName;
	   private String imageName;
}