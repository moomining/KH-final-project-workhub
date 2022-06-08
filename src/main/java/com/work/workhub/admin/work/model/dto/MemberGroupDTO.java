package com.work.workhub.admin.work.model.dto;

import java.sql.Date;
import java.util.List;

import com.work.workhub.member.member.dto.MemberDTO;

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
public class MemberGroupDTO {
	
	private int gmNo;
	private int no;
	private int groupNo;
	private Date gmDate; 
	private String groupStatus;
	private Date gmModdate;
	
	//MemberDTO의 사원이름
	private String name;
	//WorkGroupDTO의 근무그룹 이름
	private String groupName;
	private int writeNo;
	private String writeName;
	
	
	public List<MemberDTO> memberList;
	public List<WorkGroupDTO> workGroupList;
	
	
}
