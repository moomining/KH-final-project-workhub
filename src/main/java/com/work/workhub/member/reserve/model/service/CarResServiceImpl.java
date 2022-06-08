package com.work.workhub.member.reserve.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.workhub.admin.reserve.model.dto.CarDTO;
import com.work.workhub.member.reserve.model.dao.CarResMapper;
import com.work.workhub.member.reserve.model.dto.ResCarDTO;

@Service("carResService")
@Transactional
public class CarResServiceImpl implements CarResService{

	private CarResMapper carResMapper;
	
	@Autowired
	public CarResServiceImpl(CarResMapper carResMapper) {
		this.carResMapper = carResMapper;
	}
	
	@Override
	public List<CarDTO> selectAllCar() {
		return carResMapper.selectCarList();
	}
	@Override
	public List<ResCarDTO> selectAllResCarList() {
		return carResMapper.selectAllResCarList();
	}
	@Override
	public CarDTO selectedCar(int carIndex) {
		return carResMapper.selectedCar(carIndex);
	}
	
	/* 차량 예약 메소드 */
	@Override
	public boolean registReservation(ResCarDTO resCar) throws Exception {
		int result = carResMapper.reserveCar(resCar);
		
		if(result <= 0) {
			throw new Exception("차량 예약 실패");
		}
		
		
		return result > 0 ? true : false;	
	}

	
	/* 차량 반납 메소드 */
	@Override
	public boolean updateCarStatus(ResCarDTO car) throws Exception {
		int result = carResMapper.updateCarStatus(car);
		
		if(result <= 0) {
			throw new Exception("차량 반납 실패");
		}
		
		
		return result > 0 ? true : false;	
	}

	/* 차량 예약 수정 메소드 */
	@Override
	public boolean modifyCarRes(ResCarDTO car) throws Exception {
		int result = carResMapper.modifyCarRes(car);
		
		if(result <= 0) {
			throw new Exception("차량 예약 수정 실패");
		}
		
		
		return result > 0 ? true : false;	
	}

	@Override
	public void reserveCar(ResCarDTO car) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<ResCarDTO> selectResCarList() {
		return carResMapper.selectResCarList();
	}

	@Override
	public List<CarDTO> selectReservableCar() {
		return carResMapper.selectReservableCar();
	}

	
	
	
}
