package com.work.workhub.member.employ.model.dto;



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
public class AttDTO {
   
	//근태관리
	
   private int attNo;
   private int no;
   private int workNo;
   private String workName;
   private String name;
   @DateTimeFormat(pattern="yyyy-MM-dd")
   private String attGo;
   private Date attOff;
   private Date attAdd;
   private Date attOver;
   private Date attNight;
   private String attReason;
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private String attDate;
   private String attStatus;
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private String attModdate;
   //일만 자른 것 
   private String attDateSub;
   
  
}