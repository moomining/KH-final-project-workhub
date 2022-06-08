package com.work.workhub.admin.workgroup.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkGroupDTO {
   
   private int groupNo;
   private String groupName;
   private String groupEx;
   private int groupDay;
   private Date groupFirdate;
   private Date groupEnddate;
   private Date groupFirrest;
   private Date groupEndrest;
   private String groupStatus;
   private int groupRest;
   private Date groupDate;
   private Date groupModdate;
  
}