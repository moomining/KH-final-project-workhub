package com.work.workhub.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.workhub.member.member.dao.MemberMapper;
import com.work.workhub.member.message.model.dto.ChatDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {
	
	private Map<String, WebSocketSession> userSessionsMap = new ConcurrentHashMap<>();
	private MemberMapper memberMapper;
	
	@Autowired
	private ObjectMapper om;   //json 
	
	@Autowired
	public WebSocketHandler(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {  //클라이언트와 서버 
		
		log.info("Socket 연결");
		log.info(currentUserName(session)); // 현재 접속한 사원 
		userSessionsMap.put(currentUserName(session), session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception { // 메시지 
		
		String msg = message.getPayload();
		ChatDTO chatDTO = om.readValue(msg, ChatDTO.class);
		log.info("chatDTO : {}", chatDTO);
		
		if(msg != null) {
			
			String[] receivers = chatDTO.getReceiver().split(",");
			chatDTO.setReceiver(null);
			TextMessage txtMsg = new TextMessage(om.writeValueAsString(chatDTO));
			for(String receiver : receivers) {
				WebSocketSession receiverSession = userSessionsMap.get(receiver);
				if(receiverSession != null) {
					receiverSession.sendMessage(txtMsg);
				}
			}
			session.sendMessage(txtMsg);
			}
		}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		log.info("Socket 끊음");
		userSessionsMap.remove(currentUserName(session), session);
	}
	
	
	private String currentUserName(WebSocketSession session) {
		String mid = session.getPrincipal().getName();
		return mid;
	}
	
	

}
