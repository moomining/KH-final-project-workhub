package com.work.workhub.member.member.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.work.workhub.admin.workgroup.model.dto.WorkGroupDTO;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserImpl extends User{
	
	   private int no;
	   private String id;
	   private String pwd;
	   private int depNo;
	   private int positionNo;
	   private String positionName;
	   private int groupNo;
	   private String name;
	   private String email;
	   private String phone;
	   private String status;
	   private String depName;
	   
	   public List<MemberRoleDTO> memberRoleList;
	   
	   private DepartmentDTO dept;
	   private PositionDTO position;
	   private WorkGroupDTO workGroup;
	

	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public void setDetails(MemberDTO member) {
		this.no = member.getNo();
		this.id = member.getId();
		this.pwd = member.getPwd();
		this.depNo = member.getDepNo();
		this.positionNo = member.getPositionNo();
		this.positionName = member.getPositionName();
		this.groupNo = member.getGroupNo();
		this.name = member.getName();
		this.email = member.getEmail();
		this.phone = member.getPhone();
		this.status = member.getStatus();
		this.memberRoleList = member.getMemberRoleList();
		this.dept = member.getDept();
		this.position = member.getPosition();
		this.workGroup = member.getWorkGroup();
		this.depName = member.getDepName();
	}

}
