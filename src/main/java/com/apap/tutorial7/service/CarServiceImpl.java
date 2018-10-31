package com.apap.tutorial7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.repository.CarDb;

@Service
@Transactional
public class CarServiceImpl implements CarService{
	@Autowired
	private CarDb carDb;
	
	@Override
	public CarModel addCar(CarModel car) {
		return carDb.save(car);
	}
	@Override
	public List<CarModel> sortByPriceDesc(Long dealer_id) {
		return carDb.findByDealerIdOrderByPriceDesc(dealer_id);
	}
	
	@Override
	public List<CarModel> getListCarOrderByPriceAsc(long dealerId) {
		return carDb.findByDealerIdOrderByPriceAsc(dealerId);
	}

	@Override
	public void deleteCar(CarModel car) {
		carDb.delete(car);
	}
	
	@Override
	public void deleteCarById(Long id) {
		carDb.deleteById(id);
	}
	
	@Override
	public List<CarModel> getAllCar(){
		return carDb.findAll();
	}

	@Override
	public CarModel getCar(Long id) {
		return carDb.findById(id).get();
	}
	
	@Override
	public void updateCar(long id, CarModel newCar) {
		CarModel carUpdated = carDb.getOne(id);
		carUpdated.setBrand(newCar.getBrand());
		carUpdated.setType(newCar.getType());
		carUpdated.setPrice(newCar.getPrice());
		carUpdated.setAmount(newCar.getAmount());
		carDb.save(carUpdated);
	}
	
}
