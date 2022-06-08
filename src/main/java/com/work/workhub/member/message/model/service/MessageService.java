package com.work.workhub.member.message.model.service;

import java.util.List;
import java.util.Map;

import com.work.workhub.member.member.dto.DepartmentDTO;
import com.work.workhub.member.member.dto.MemberDTO;
import com.work.workhub.member.message.model.dto.MessageDTO;

public interface MessageService {
	
	List<MessageDTO> findMessageInbox(int no);
	
	MessageDTO findMessageInboxByCode(int msgCode);

	List<MessageDTO> findMessageSent(int no);

	List<MessageDTO> findMessageRecyclebinList(int no);

	void sendMessage(MessageDTO message) throws Exception;

	List<DepartmentDTO> selectDepartmentList();

	List<MemberDTO> selectMemberList();

	void deleteMessage(String code);

	Map<Integer, List<MemberDTO>> selectMemberListMap(List<DepartmentDTO> departmentList, List<MemberDTO> memberList);

	void updateMessageForView(int msgCode);
	
}
