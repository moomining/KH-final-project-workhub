package com.work.workhub.member.reserve.model.service;

import java.util.List;

import com.work.workhub.admin.reserve.model.dto.CarDTO;
import com.work.workhub.member.reserve.model.dto.ResCarDTO;

public interface CarResService {

	List<CarDTO> selectAllCar();

	List<ResCarDTO> selectAllResCarList();

	CarDTO selectedCar(int carIndex);

	boolean registReservation(ResCarDTO resCar) throws Exception;

	boolean updateCarStatus(ResCarDTO car) throws Exception;
	
	boolean modifyCarRes(ResCarDTO car) throws Exception;

	void reserveCar(ResCarDTO car);

	List<ResCarDTO> selectResCarList();

	List<CarDTO> selectReservableCar();

	
	

}
