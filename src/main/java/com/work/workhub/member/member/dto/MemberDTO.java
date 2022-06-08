package com.work.workhub.member.member.dto;

import java.util.List;

import com.work.workhub.admin.workgroup.model.dto.WorkGroupDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
   
   private int no;
   private String id;
   private String pwd;
   private int depNo;
   private String depName;
   private int positionNo;
   private String positionName;
   private int groupNo;
   private String name;
   private String gender;
   private String email;
   private String phone;
   private String status;
   private String sequenceName;
   
   public List<MemberRoleDTO> memberRoleList;
   
   private DepartmentDTO dept;
   private PositionDTO position;
   private WorkGroupDTO workGroup;
   
   
}