package com.work.workhub.admin.reserve.model.service;

import java.util.List;

import com.work.workhub.admin.reserve.model.dto.CarDTO;

public interface CarService {

	boolean registCar(CarDTO car) throws Exception;

	boolean modifyCar(CarDTO car) throws Exception;

	List<CarDTO> selectAllCar();

	CarDTO selectCarInfo(int no);

	boolean deleteCar(CarDTO car) throws Exception;
}
