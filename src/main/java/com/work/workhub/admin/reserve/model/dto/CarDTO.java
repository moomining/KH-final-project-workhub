package com.work.workhub.admin.reserve.model.dto;

import com.work.workhub.member.reserve.model.dto.ResCarDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
	
	private int carNo;
	private String carName;
	private int carNumber;
	private String carYn;
	private String delYn;
	
	private ResCarDTO carRes;

}
