package com.work.workhub.member.approval.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.member.approval.model.dto.AcceptDTO;
import com.work.workhub.member.approval.model.dto.AppLineDTO;
import com.work.workhub.member.approval.model.dto.ApprovalDTO;
import com.work.workhub.member.approval.model.dto.ReferenceDTO;
import com.work.workhub.member.approval.model.service.ApprovalService;
import com.work.workhub.member.member.dto.DepartmentDTO;
import com.work.workhub.member.member.dto.MemberDTO;
import com.work.workhub.member.member.dto.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/approval")
public class ApprovalController {

	private ApprovalService approvalService;
	private MessageSource messageSource;

	@Autowired
	public ApprovalController(ApprovalService approvalService, MessageSource messageSource) {
		this.approvalService = approvalService;
		this.messageSource = messageSource;
	}

	@GetMapping("regist")
	public ModelAndView registPage(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {

		List<DepartmentDTO> departmentList = approvalService.selectDepartmentList();
		List<MemberDTO> memberList = approvalService.selectMemberList();

		mv.addObject("departmentList", departmentList);
		mv.setViewName("approval/regist");

		mv.addObject("memberList", memberList);
		mv.setViewName("approval/regist");

		log.info("부서 목록 : {}", departmentList);
		log.info("사원 목록 : {}", memberList);

		return mv;
	}

	@PostMapping("regist")
	public String registApproval(@ModelAttribute ApprovalDTO approval, @RequestParam("receiver") String receiver,
			@RequestParam("ref") String ref, @AuthenticationPrincipal UserImpl user, RedirectAttributes rttr,
			Locale locale) throws Exception {

		approval.setNo(user.getNo());

		log.info("결재문서 등록 : {}", approval);

		int receiverNo = Integer.parseInt(receiver);
		int refNo = Integer.parseInt(ref);

		approvalService.registApproval(approval);
		approvalService.insertReceiver(approval, receiverNo);
		log.info("결재자 사번 : {}", receiverNo);
		approvalService.insertRef(approval, refNo);
		log.info("참조자 사번 : {}", refNo);

		rttr.addFlashAttribute("successMessage", messageSource.getMessage("registApproval", null, locale));

		return "redirect:/approval/sendList";

	}

	@GetMapping("receptionList")
	public ModelAndView receptionList(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {

		int no = user.getNo();
		
		List<ApprovalDTO> receptionList = approvalService.selectReceptionList(no);

		mv.addObject("receptionList", receptionList);

		log.info("수신목록 : {}", receptionList.toString());

		return mv;
	}

	@GetMapping("sendList")
	public ModelAndView sendList(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {

		int no = user.getNo();

		List<ApprovalDTO> sendList = approvalService.selectSendList(no);

		mv.addObject("sendList", sendList);
		mv.setViewName("approval/sendList");

		log.info("발신목록 : {}", sendList.toString());
//		log.error("발신목록 : {}", sendList.toString());

		return mv;
	}

	@GetMapping("receptionDetail")
	public ModelAndView receptionDetail(ModelAndView mv, @AuthenticationPrincipal UserImpl user,
			@RequestParam("approvalNo") Integer approvalNo) {

		ApprovalDTO approval = approvalService.findAppByNo(approvalNo);
		List<AppLineDTO> receiver = approvalService.findReceiverByNo(approvalNo);
		List<ReferenceDTO> ref = approvalService.findRefByNo(approvalNo);
		AcceptDTO accept = approvalService.findAccByNo(approvalNo);

		log.info("결재 조회 : {}", accept);
		
		mv.addObject("approvalDTO", approval);
		mv.addObject("receiver", receiver);
		mv.addObject("ref", ref);
		mv.addObject("accept", accept);

		mv.setViewName("/approval/receptionDetail");

		return mv;

	}

	@GetMapping("sendDetail")
	public ModelAndView sendDetail(ModelAndView mv, @AuthenticationPrincipal UserImpl user,
			@RequestParam("approvalNo") Integer approvalNo) {

		ApprovalDTO approval = approvalService.findAppByNo(approvalNo);
		List<AppLineDTO> receiver = approvalService.findReceiverByNo(approvalNo);
		List<ReferenceDTO> ref = approvalService.findRefByNo(approvalNo);
		AcceptDTO accept = approvalService.findAccByNo(approvalNo);

		mv.addObject("approvalDTO", approval);
		mv.addObject("receiver", receiver);
		mv.addObject("ref", ref);
		mv.addObject("accept", accept);

		mv.setViewName("/approval/sendDetail");

		return mv;
	}

	// 회수 버튼 눌렀을 때 상태 회수로 바꾸기
	@PostMapping("/retrieve")
	@ResponseBody
	public void modifyRet(@RequestParam("approvalNo") String approvalNo) {
		int no = Integer.parseInt(approvalNo);
		approvalService.modifyRet(no);

	}

	// 삭제
	@PostMapping("/delete")
	@ResponseBody
	public void deleteApp(@RequestParam("approvalNo") String approvlNo) {
		int no = Integer.parseInt(approvlNo);
		
		approvalService.deleteLine(no);
		approvalService.deleteRef(no);
		approvalService.deleteApp(no);
	}

	//수정 getMapping
	@GetMapping("modify/no/{no}")
	public ModelAndView modifyApp(ModelAndView mv, @PathVariable("no") String no) {

		int approvalNo = Integer.parseInt(no);

		ApprovalDTO approval = approvalService.findAppByNo(approvalNo);
		List<AppLineDTO> receiver = approvalService.findReceiverByNo(approvalNo);
		List<ReferenceDTO> ref = approvalService.findRefByNo(approvalNo);

		log.info("수정문서 결재자 : {}", receiver.toString());
		log.info("수정문서 참조자 : {}", ref.toString());

		List<DepartmentDTO> departmentList = approvalService.selectDepartmentList();
		List<MemberDTO> memberList = approvalService.selectMemberList();

		mv.addObject("approval", approval);
		mv.addObject("receiver", receiver);
		mv.addObject("ref", ref);
		mv.addObject("departmentList", departmentList);
		mv.addObject("memberList", memberList);

		mv.setViewName("/approval/modify");

		return mv;
	}

	//수정 postMapping
	@PostMapping("modify/no/{no}")
	public String modifyApp(@ModelAttribute ApprovalDTO approval, @PathVariable("no") String no,
			@RequestParam("receiver") String receiver, @RequestParam("ref") String ref,
			@AuthenticationPrincipal UserImpl user, RedirectAttributes rttr, Locale locale) {

		approval.setNo(Integer.parseInt(no));

		log.info("결재문서 수정 : {}", approval);

		int receiverNo = Integer.parseInt(receiver);
		int refNo = Integer.parseInt(ref);

		approvalService.modifyApp(approval);
		
		approvalService.modifyReceiver(Integer.parseInt(no), receiverNo);
		approvalService.modifyRef(Integer.parseInt(no), refNo);
		 

		rttr.addFlashAttribute("successMessage", messageSource.getMessage("registApproval", null, locale));

		return "redirect:/approval/sendList";
	}
	
	//승인
	@PostMapping("/accept")
	@ResponseBody
	public void acceptApp(@RequestParam("approvalNo") String approvalNo, @AuthenticationPrincipal UserImpl user) {
		int no = Integer.parseInt(approvalNo);
		int accNo = user.getNo();
		
		approvalService.acceptApp(no, accNo);
		approvalService.modifyStatus(no);
	}
	
	//반려
	@PostMapping("/reject")
	@ResponseBody
	public void rejectApp(@RequestParam("approvalNo") String approvalNo, @AuthenticationPrincipal UserImpl user) {
		int no = Integer.parseInt(approvalNo);
		int rejNo = user.getNo();
		
		approvalService.rejectApp(no, rejNo);
		approvalService.rejStatus(no);
	}

}
