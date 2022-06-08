package com.work.workhub.member.member.dto;

import lombok.Data;

@Data
public class MemberRoleDTO {
	
	private int memberNo;
	private int authNo;
	private String sequenceName;
	
	/* TBL_AUTHORITY - 권한 코드별로 가지는 권한을 나타냄 */
	private AuthorityDTO authority;
	

}