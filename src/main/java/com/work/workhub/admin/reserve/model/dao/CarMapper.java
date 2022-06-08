package com.work.workhub.admin.reserve.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.work.workhub.admin.reserve.model.dto.CarDTO;

@Mapper
public interface CarMapper {

	List<CarDTO> selectAllCar();

	int registCar(CarDTO car);

	CarDTO selectCarInfo(int no);

	int modifyCar(CarDTO car);

	int deleteCar(CarDTO car);
	
	
}
