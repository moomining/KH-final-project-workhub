package com.work.workhub.member.reserve.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.work.workhub.admin.reserve.model.dto.CarDTO;
import com.work.workhub.member.reserve.model.dto.ResCarDTO;

@Mapper
public interface CarResMapper {

	List<CarDTO> selectCarList();
	
	/* reserve/mylist에서 조회하기 위한 메소드 */
	List<ResCarDTO> selectAllResCarList();

	CarDTO selectedCar(int carIndex);

	int reserveCar(ResCarDTO resCar);

	int updateCarStatus(ResCarDTO car);

	int modifyCarRes(ResCarDTO car);

	/* 예약 조회 화면에서 조회하기 위한 메소드 */
	List<ResCarDTO> selectResCarList();

	List<CarDTO> selectReservableCar();


}
