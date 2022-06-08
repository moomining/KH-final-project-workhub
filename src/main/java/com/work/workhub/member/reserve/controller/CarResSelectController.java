package com.work.workhub.member.reserve.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.admin.reserve.model.dto.CarDTO;
import com.work.workhub.member.member.dto.UserImpl;
import com.work.workhub.member.reserve.model.dto.ResCarDTO;
import com.work.workhub.member.reserve.model.service.CarResService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/reserve/car")
public class CarResSelectController {

	private CarResService carResService;
	private MessageSource messageSource;

	@Autowired
	public CarResSelectController(CarResService carResService, MessageSource messageSource) {
		this.carResService = carResService;
		this.messageSource = messageSource;
	}

	@GetMapping("list")
	public ModelAndView selectCarList(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		
		log.info("로그인 유저 정보 : {}", user);

		List<CarDTO> carList = carResService.selectAllCar();
		List<CarDTO> reservableCar = carResService.selectReservableCar();
		
//		List<CarDTO> newList = reservableCar.subList(0,2);

		mv.addObject("carList", carList);
		mv.addObject("reservableCar",reservableCar);
		
		log.info("등록한 차량 목록 : {} ", carList);
		log.info("reservableCar : {}", reservableCar);
		
//		log.info("newList : {}", newList);
		
		mv.setViewName("reserve/car/list");

		return mv;
	}
	
	@PostMapping("list")
	public String registCarReserve(@ModelAttribute ResCarDTO resCar, RedirectAttributes rttr, Locale locale, @RequestParam int memberNo) throws Exception {
		log.info("memberNo : {}", memberNo);
		
		log.error("등록요청 : {}",resCar);
		log.warn("등록요청 : {}",resCar);
		log.info("등록요청 : {}",resCar);
		
		carResService.registReservation(resCar);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("registCarReservation", null, locale));
		
		return "redirect:/reserve/car/list";
	}
	

}
