package com.work.workhub.member.message.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.workhub.member.member.dto.DepartmentDTO;
import com.work.workhub.member.member.dto.MemberDTO;
import com.work.workhub.member.message.model.dao.MessageMapper;
import com.work.workhub.member.message.model.dto.MessageDTO;

@Service("listService")
@Transactional
public class MessageServiceImpl implements MessageService {
	
	private final MessageMapper messageMapper;
	
	@Autowired
	public MessageServiceImpl(MessageMapper messageMapper) {
		this.messageMapper = messageMapper;
	}

	/* 쪽지 - 받은 쪽지함 조회 */
	@Override
	public List<MessageDTO> findMessageInbox(int no) {
		return messageMapper.findMessageInbox(no);
	}
	
	/* 쪽지 - 쪽지 상세 조회 */
	@Override
	public MessageDTO findMessageInboxByCode(int msgCode) {
		return messageMapper.findMessageInboxByCode(msgCode);
	}

	/* 쪽지 - 보낸 쪽지함 조회 */
	@Override
	public List<MessageDTO> findMessageSent(int no) {
		return messageMapper.findMessageSent(no);
	}

	/* 쪽지 - 휴지통 조회 */
	@Override
	public List<MessageDTO> findMessageRecyclebinList(int no) {
		return messageMapper.findMessageRecyclebinList(no);
	}

	/* 쪽지 - 새 쪽지 보내기 */
	@Override
	public void sendMessage(MessageDTO message) throws Exception {
		/* memberNos 스플릿으로 잘라서 for문 돌려서 넘기기 */
		//널값 체크하기
		String[] memberNos = message.getMemberNos().split(",");
//		for(String memberNo : memberNos) {
//			MessageDTO messageTmp = new MessageDTO();
//			messageTmp.setReicipientNo(Integer.parseInt(memberNo));
//			messageTmp.setMsgContent(message.getMsgContent());
//			messageMapper.sendMessage(messageTmp);
//		}
		for(String memberNo : memberNos) {
			message.setReicipientNo(Integer.parseInt(memberNo));
			messageMapper.sendMessage(message);
		}
		
	}

	@Override
	public List<DepartmentDTO> selectDepartmentList() {
		return messageMapper.selectDepartmentList();
	}

	@Override
	public List<MemberDTO> selectMemberList() {
		return messageMapper.selectMemberList();
	}

	@Override
	public void deleteMessage(String code) {
		messageMapper.deleteMessage(code);
	}

	@Override
	public Map<Integer, List<MemberDTO>> selectMemberListMap(List<DepartmentDTO> departmentList, List<MemberDTO> memberList) {
		
		Map<Integer,List<MemberDTO>> memberListMap = new HashMap<>();
	      
	    for(DepartmentDTO dto: departmentList) {
	       if(!memberListMap.containsKey(dto.getDepNo())){
	            memberListMap.put(dto.getDepNo(), new ArrayList<>());
	       }
	       for(MemberDTO memberDTO : memberList) {
	         if(memberDTO.getDepNo()==dto.getDepNo()) {
	             memberListMap.get(dto.getDepNo()).add(memberDTO);
	         }
	       }
	    }
	    
		return memberListMap;
	}

	@Override
	public void updateMessageForView(int msgCode) {
		messageMapper.updateMessageForView(msgCode);
		
	}


}
