package com.work.workhub.member.message.model.dto;

import com.work.workhub.member.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
	
	private int msgCode;			//쪽지번호 
	private int senderNo;			//발신자사번 
	private int reicipientNo;		//수신자사번 
	private String msgContent;		//쪽지내용 
	private String sendDate;		//발신날짜 
	private String sendTime;		//발신시간 
	private String readDate;		//읽은날짜 
	private String readTime;		//읽은시간 
	private String recycleBinStatus;//휴지통여부 
	private String deleteStatus;	//삭제여부 
	
	private MemberDTO sender; //발신자 가져오기
	private MemberDTO reicipient; // 수신자 가져오기
	
	private String memberNos; //수신자사번 모아서전달  
	
//	public String getSendDate() {
//	      try {
//	         Date date = new SimpleDateFormat("yyyyMMdd").parse(this.sendDate);
//	         return new SimpleDateFormat("yyyy-MM-dd").format(date);
//	      } catch (ParseException e) {
//	         e.printStackTrace();
//	      }
//	      return null;
//	   }

}
