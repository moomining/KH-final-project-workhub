package com.work.workhub.member.message.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.member.member.dto.DepartmentDTO;
import com.work.workhub.member.member.dto.MemberDTO;
import com.work.workhub.member.member.dto.UserImpl;
import com.work.workhub.member.message.model.dto.MessageDTO;
import com.work.workhub.member.message.model.service.MessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/message")
public class MessageController {
	
	private MessageService messageService;
	private MessageSource messageSource;
	
	@Autowired
	public MessageController(MessageService messageService, MessageSource messageSource) {
		this.messageService = messageService;
		this.messageSource = messageSource;
	}
	
	/* 받은 쪽지함 조회 */
	@GetMapping("inboxList")
	public ModelAndView findMessageInboxList(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		
		int no = user.getNo();
		
		List<MessageDTO> messageInboxList = messageService.findMessageInbox(no);
		
		mv.addObject("messageInboxList", messageInboxList);
		mv.setViewName("message/inboxList");
		
		return mv;
		
	}
	
	/* 보낸 쪽지함 조회 */
	@GetMapping("sentList")
	public ModelAndView findMessageSentList(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		
		int no = user.getNo();
		
		List<MessageDTO> messageSentList = messageService.findMessageSent(no);
		
		
		mv.addObject("messageSentList", messageSentList);
		mv.setViewName("message/sentList");
		
		return mv;
	}
	
	/* 휴지통 조회 */
	@GetMapping("recyclebinList")
	public ModelAndView findMessageRecyclebinList(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		
		int no = user.getNo();
		
		List<MessageDTO> messageRecyclebinList = messageService.findMessageRecyclebinList(no);
		
		mv.addObject("messageRecyclebinList", messageRecyclebinList);
		mv.setViewName("message/recyclebinList");
		
		return mv;
	}
	
	/* 쪽지 보내기 화면 연결 */
	@GetMapping("sendMessage")
	public ModelAndView sendMessagePage(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		
		List<DepartmentDTO> departmentList = messageService.selectDepartmentList();
		List<MemberDTO> memberList = messageService.selectMemberList();
		
		Map<Integer, List<MemberDTO>> memberListMap = messageService.selectMemberListMap(departmentList, memberList);
		
		mv.addObject("departmentList", departmentList);
		mv.addObject("memberList", memberList);
		mv.addObject("memberListMap", memberListMap);
		mv.setViewName("message/sendMessage");
		
		log.info("부서 목록 : {}", departmentList);
		log.info("사원 목록 : {}", memberList);
		
		return mv;
	}
	
	/* 쪽지 보내기 */
	@PostMapping("sendMessage")
	public String sendMessage(@ModelAttribute MessageDTO message, RedirectAttributes rttr, Locale locale, @AuthenticationPrincipal UserImpl user) throws Exception {
		System.out.println(message);
		message.setSenderNo(user.getNo());
		
		messageService.sendMessage(message);
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("sendMessage", null, locale));
		
		log.info("쪽지 목록 : {}", message);
		return "redirect:/message/inboxList";
	}
	
	/* 쪽지 삭제(휴지통 이동) */
	@PostMapping("delete")
	public String deleteMessage(@ModelAttribute MessageDTO message, @RequestParam String[] valueArr, RedirectAttributes rttr, Locale locale) throws Exception {
		
		int size = valueArr.length;
		for(int i = 0; i < size; i++) {
			messageService.deleteMessage(valueArr[i]);
		}
		
		log.info(valueArr.toString());
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("deleteMessage", null, locale));
		
		return "redirect:/message/inboxList";
	}
	
	/* 쪽지 읽었을 때 읽은 날짜/시간 값 UPDATE */
	@PutMapping("view/{msgCode}")
	public @ResponseBody int updateMessageForView(@PathVariable("msgCode") int msgCode) {
		
		log.info("쪽지 번호 : {}", msgCode);
		messageService.updateMessageForView(msgCode);
		
		return msgCode;
		
	}
	
	@GetMapping("view/{msgCode}")
	   public @ResponseBody MessageDTO findMessageInboxByCode(@PathVariable int msgCode) {
	      return messageService.findMessageInboxByCode(msgCode);
	   }
	
	
}