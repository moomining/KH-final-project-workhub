package com.work.workhub.member.calendar.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.work.workhub.member.member.dto.DepartmentDTO;
import com.work.workhub.member.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDTO {
	
	private int calNo;            //일정번호
	private int calCategoryCode;    //카테고리코드
	private int calDepNo;              //부서번호
	private int calWriterNo;           //작성자사원번호
	private String calTitle;            //일정제목
	private String calContent;         //일정내용 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date calStart;       //시작날짜
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date calEnd;         //종료날짜
	private String calAlldayStatus;    //종일여부 
	
	private CategoryDTO calCategory;
	private DepartmentDTO department;
	private MemberDTO member;

}
