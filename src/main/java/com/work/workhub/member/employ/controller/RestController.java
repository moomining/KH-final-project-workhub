package com.work.workhub.member.employ.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.admin.reserve.model.dto.MeetingRoomDTO;
import com.work.workhub.admin.work.model.dto.RestCateDTO;
import com.work.workhub.admin.work.model.dto.WorkDTO;
import com.work.workhub.member.employ.model.dto.AttDTO;
import com.work.workhub.member.employ.model.dto.ImgDTO;
import com.work.workhub.member.employ.model.dto.RestDTO;
import com.work.workhub.member.employ.model.service.RestService;
import com.work.workhub.member.member.dto.UserImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/rest")
public class RestController {
	
	private RestService restService;
	private MessageSource messageSource;
	private String uploadFilePath;
	
	@Autowired
	public RestController(RestService restService, MessageSource messageSource,  @Value("${custom.path.upload-files}") String uploadFilePath) {
		this.restService = restService;
		this.messageSource = messageSource;
		this.uploadFilePath = uploadFilePath;
	}
	
	
		/* 근무유형 select box*/		
		@GetMapping("restSelect")
		public ModelAndView restSelect(ModelAndView mv) {
			
			//근무 유형 select box
			List<WorkDTO> attsList = restService.selectsBox();
			//연차 유형 사용한 것
			List<RestDTO> resList = restService.selectrest();
			//연차 유형 사용하지 않은 것
			List<RestDTO> ressList = restService.selectrests();
			
			mv.addObject("attsList",attsList);
			mv.addObject("resList",resList);
			mv.addObject("ressList",ressList);
			
			mv.setViewName("rest/restSelect");
			
			return mv;
		}	
		
		
		//휴가 신청서
		@GetMapping("restWrite")
		public ModelAndView restWrite(ModelAndView mv) {
		
		//휴가 유형 select box
		List<RestCateDTO> rcList = restService.rcBox();
		

		mv.addObject("rcList",rcList);
		
		
		mv.setViewName("rest/restWrite");
		
		return mv;
		
		}
		
		
		//연차 신청서 작성
		 @PostMapping("write")
		    public String registRest(@RequestParam(value="singleFile", required=false) MultipartFile singleFile, Model model, @ModelAttribute ImgDTO img,@ModelAttribute RestDTO rest, RedirectAttributes rttr, Locale locale,@AuthenticationPrincipal UserImpl user) throws Exception {
			 
			 	//사원 등록
			 	rest.setNo(user.getNo());
			 
		    	/* 연차 신청서 작성*/
		        log.info("등록요청메뉴 : {}",img);

				
		        /* 연차신청서 사진 작성 */
				log.info("singleFile : {}" + singleFile);

				// file 저장 경로 설정

				File mkdir = new File(uploadFilePath);
				if (!mkdir.exists()) {
					mkdir.mkdirs();
				}

				// file name change
				String name = singleFile.getOriginalFilename();
				String ext = name.substring(name.lastIndexOf("."));
				String reName = UUID.randomUUID().toString().replace("-", "") + ext;

				// file Save
				try {
					singleFile.transferTo(new File(uploadFilePath + "\\" + reName));
					model.addAttribute("message", "파일 업로드 성공");

				} catch (IllegalStateException | IOException e) {
					model.addAttribute("message", "파일 업로드 실패");
				}

				img.setImageName(name);
				img.setImageRename(reName);
				img.setImagePath(uploadFilePath);
				
				log.info("room info : {}", img);
				
				//이미지 삽입 
				restService.insertRest(img, rest);
				//파라미터 삽입
//				restService.insertPara(rest);
				
		        rttr.addFlashAttribute("successMessage", messageSource.getMessage("insertRest", null, locale));
		        
		        return "redirect:/rest/restSelect";

		    }
		 
		 
		 //연차 신청서 옛날값
			@GetMapping("restModify")
			public ModelAndView restModify(ModelAndView mv,@RequestParam int porestNo ) {
			
			//연차신청서 예전 파라미터 
			List<RestDTO> paList = restService.paModify(porestNo);
			
			//휴가 유형 select box
			List<RestCateDTO> reList = restService.reBox();

			mv.addObject("paList",paList);
			mv.addObject("reList",reList);

			mv.setViewName("rest/restModify");
			
			return mv;
			
			}		 
		 
		 
		 	//연차신청서 수정하기
			@PostMapping("modify")
			public String modifyRest(@RequestParam int porestNo, @RequestParam(value="singleFile", required=false) MultipartFile singleFile, @ModelAttribute ImgDTO img, @ModelAttribute RestDTO rest,HttpServletRequest request, Model model, RedirectAttributes rttr, Locale locale) throws Exception {
				
				
				
				//수정하기 버튼을 눌렀을 때 해당 게시글의 이미지 정보 가져오기
				ImgDTO beforeImg = restService.modifyImgs(porestNo);
				
				log.info("수정될 이미지 결과값 : {}", beforeImg);
				
				if (singleFile != null) {
					/* 회의실 사진 등록 */
					log.info("singleFile : {}" + singleFile);
					
					// file 저장 경로 설정
					String root = request.getSession().getServletContext().getRealPath("resources");
					
					log.info("루트 확인 : {}",root);
					
					

					String savePath = "C:/Users/Administrator/git/workhub/src/main/resources/uploadFiles";

					File mkdir = new File(savePath);
					if (!mkdir.exists()) {
						mkdir.mkdirs();
					}

					// file name change
					String name = singleFile.getOriginalFilename();
					String ext = name.substring(name.lastIndexOf("."));
					String reName = UUID.randomUUID().toString().replace("-", "") + ext;

					// file Save
					try {
						singleFile.transferTo(new File(uploadFilePath + "\\" + reName));
						model.addAttribute("message", "파일 업로드 성공");

					} catch (IllegalStateException | IOException e) {
						model.addAttribute("message", "파일 업로드 실패");
					}

					img.setImageName(name);
					img.setImageRename(reName);
					img.setImagePath(savePath);
					
					log.info("room info : {}", img);
					
					
					/* 기존 있던 파일 삭제.*/
					File deleteFile = new File(savePath + "/" + beforeImg.getImageRename());
					boolean isDeleted = deleteFile.delete();
					
					if(isDeleted == true) {
						log.info("기존 파일 삭제 완료");
					} else {
						log.info("기존 파일 삭제 실패");
					}
					
				}
				
				
				
				//연차신청서 수정
				restService.modifyRestImg(img, rest);
				
				//메시지 주석처리 해둠 나중에 주석 제거
				rttr.addFlashAttribute("successMessage", messageSource.getMessage("modifyRestImg", null, locale));
				
				return "redirect:/rest/restSelect";
				
			}
		 

			@GetMapping("restDelete")
			public String restDelete(@RequestParam int porestNo, @RequestParam(value="singleFile", required=false) MultipartFile singleFile, @ModelAttribute ImgDTO img, @ModelAttribute RestDTO rest, HttpServletRequest request, RedirectAttributes rttr, Locale locale) throws Exception {
				
				//삭제하기 버튼을 눌렀을 때 해당 게시글의 이미지 정보 가져오기
				ImgDTO beforeImg = restService.modifyImgss(porestNo);
				
				log.info("삭제할 연차신청서 : {}",beforeImg);
				
				restService.deleteRest(rest,porestNo);
				
				String root = request.getSession().getServletContext().getRealPath("resources");
				
				log.info("루트 확인 : {}",root);
				
//				String savePath = root + "\\uploadFiles";
				
				String savePath = "C:/Users/Administrator/git/workhub/src/main/resources/uploadFiles";
				
				log.info("savePath 확인 : {}",savePath);
				
				File deleteFile = new File(savePath + "/" + beforeImg.getImageRename());
				boolean isDeleted = deleteFile.delete();
				
				if(isDeleted == true) {
					log.info("기존 파일 삭제 완료");
				} else {
					log.info("기존 파일 삭제 실패");
				}
				
				rttr.addFlashAttribute("successMessage", messageSource.getMessage("deleteRest", null, locale));
				
				return "redirect:/rest/restSelect";
				
			}

		 
		 
		 
}				
