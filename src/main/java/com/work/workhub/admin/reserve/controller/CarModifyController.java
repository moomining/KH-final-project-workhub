package com.work.workhub.admin.reserve.controller;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.admin.reserve.model.dto.CarDTO;
import com.work.workhub.admin.reserve.model.service.CarService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("asset/car")
public class CarModifyController {

	private CarService carService;
	private MessageSource messageSource;
	
	@Autowired
	public CarModifyController(CarService carService, MessageSource messageSource) {
		this.carService = carService;
		this.messageSource = messageSource;
	}
	
	
	@PostMapping("modify")
	public String modifyCar(@ModelAttribute("car") CarDTO car, RedirectAttributes rttr, Locale locale) throws Exception {
		
		log.info("수정할 차 : {}",car);
		
		carService.modifyCar(car);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("modifyCar", null, locale));
		
		return "redirect:/asset/car/list";
		
	}
	
	@PostMapping("delete")
	public String deleteCar(@ModelAttribute("car") CarDTO car, RedirectAttributes rttr, Locale locale) throws Exception {
		
		log.info("삭제할 차 : {}",car);
		
		carService.deleteCar(car);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("deleteCar", null, locale));
		
		return "redirect:/asset/car/list";
		
	}
}
