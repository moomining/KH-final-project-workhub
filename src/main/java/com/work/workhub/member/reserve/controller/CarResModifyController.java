package com.work.workhub.member.reserve.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.member.reserve.model.dto.ResCarDTO;
import com.work.workhub.member.reserve.model.service.CarResService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("reserve/car")
public class CarResModifyController {
	
	private CarResService carService;
	private MessageSource messageSource;
	
	@Autowired
	public CarResModifyController(CarResService carService, MessageSource messageSource) {
		this.carService = carService;
		this.messageSource = messageSource;
	}
	
	
	@GetMapping("modify")
	public void modifyCarRes() {
		
	}
	
	
	@GetMapping("modifyInfo")
	public String modifyCarRes(Model model, @RequestParam Map<String,String> carInfo) throws ParseException {
		
		String resNo = carInfo.get("resNo");
		log.info(resNo);
		String carName = carInfo.get("returnCarName");
		log.info(carName);
		String startTime = carInfo.get("returnStartTime");
		log.info(startTime);
		String endTime = carInfo.get("returnEndTime");
		log.info(endTime);
		String name = carInfo.get("returnName");
		String carPurpose = carInfo.get("returnCarPurpose");
		
        String startTime2 = null;
        String endTime2 = null;
        
        SimpleDateFormat recvSimpleFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
 
        // 여기에 원하는 포맷을 넣어주면 된다
        SimpleDateFormat tranSimpleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);

        try {
            Date data = recvSimpleFormat.parse(startTime);
            startTime2 = tranSimpleFormat.format(data);
            
            Date data2 = recvSimpleFormat.parse(endTime);
            endTime2 = tranSimpleFormat.format(data2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        log.info(startTime2);
		log.info(endTime2);
        
		Map<String, Object> carInfo2 = new HashMap<String, Object>();
		carInfo2.put("resNo", resNo);
		carInfo2.put("carName", carName);
		carInfo2.put("startTime", startTime2);
		carInfo2.put("endTime", endTime2);
		carInfo2.put("name", name);
		carInfo2.put("carPurpose", carPurpose);	
		
//		model.addAllAttributes(carInfo);
		
		model.addAttribute("carInfo", carInfo);
		model.addAttribute("carInfo2", carInfo2);
		
		log.info("carInfo : {} ", carInfo);
		log.info("carInfo2 : {} ", carInfo2);
		return "/reserve/car/modify";
		
	}
	
	/* 차량 예약 수정 메소드 */
	@PostMapping("modify")
	public String modifyCarRes(@ModelAttribute ResCarDTO car, RedirectAttributes rttr, Locale locale) throws Exception {
		
		log.error("수정할 차 정보 : {}",car);
		log.info("수정할 차 정보 : {}",car);
		
		carService.modifyCarRes(car);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("modifyCarRes", null, locale));
		
		return "redirect:/reserve/mylist";
		
	}
	
	/* 차량 예약 삭제 메소드 */
	@PostMapping("delete")
	public String updateCarStatus(@ModelAttribute ResCarDTO car, RedirectAttributes rttr, Locale locale) throws Exception {
		
		log.error("삭제할 예약 정보 : {}",car);
		log.info("삭제할 예약 정보 : {}",car);
		
		carService.updateCarStatus(car);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("returnCar", null, locale));
		
		return "redirect:/reserve/mylist";
		
	}
	
}
