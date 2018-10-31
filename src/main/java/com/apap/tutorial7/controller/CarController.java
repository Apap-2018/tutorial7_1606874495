package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.*;
import com.apap.tutorial7.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	@PostMapping()
	private CarModel addDealerSubmit(@RequestBody CarModel car) {
		DealerModel dealer = dealerService.getDealerDetailById(car.getId()).get();
		car.setDealer(dealer);
		return carService.addCar(car);
	}
	
	@GetMapping(value = "/{carId}")
	private CarModel viewCar(@PathVariable ("carId") long carId, Model model) {
		return carService.getCar(carId);
	}
	
	@GetMapping()
	private List<CarModel> viewAllDealer(Model model) {
		return carService.getAllCar();
	}
	
	@DeleteMapping(value = "/{carId}")
	private String deleteCar(@PathVariable ("carId") Long id, Model model) {
		CarModel car = carService.getCar(id);
		carService.deleteCar(car);
		return "Car has been deleted";
	}
	
	@PutMapping(value = "/{id}")
	private String updateCarSubmit(
			@PathVariable (value = "id") long id,
			@RequestParam("brand") Optional<String> brand,
			@RequestParam("type") Optional<String> type,
			@RequestParam("price") Optional<Long> price,
			@RequestParam("amount") Optional<Integer> amount,
			@RequestParam("dealerId") Optional<Long> dealerId) {
		CarModel car = (CarModel) carService.getCar(id);
		if (car.equals(null)) {
			return "Couldn't find your car";
		}
		if (brand.isPresent()) {
			car.setBrand(brand.get());
		}
		if (type.isPresent()) {
			car.setType(type.get());
		}
		if (price.isPresent()) {
			car.setPrice(price.get());
		}
		if (amount.isPresent()) {
			car.setAmount(amount.get());
		}
		if (dealerId.isPresent()) {
			DealerModel dealerModel = dealerService.getDealerDetailById(dealerId.get()).get();
			car.setDealer(dealerModel);
		}
		carService.updateCar(id, car);
		return "Car Update Success";
		
	}
	
	/*@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		DealerModel dealer  = dealerService.getDealerDetailById(dealerId).get();
		ArrayList<CarModel> list = new ArrayList<CarModel>();
		list.add(new CarModel());
		dealer.setListCar(list);
		
		model.addAttribute("dealer", dealer);
		return "addCar";
	}
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params={"save"})
	private String addCarSubmit(@ModelAttribute DealerModel dealer) {
		DealerModel dealerNow  = dealerService.getDealerDetailById(dealer.getId()).get();
		for(CarModel car: dealer.getListCar()) {
			car.setDealer(dealerNow);
			carService.addCar(car);
		}
		return "add";
	}
	
	@RequestMapping(value = "/car/delete", method = RequestMethod.POST )
	private String deleteCar(@ModelAttribute DealerModel dealer, Model model) {
		for(CarModel car: dealer.getListCar()) {
			carService.deleteCar(car);
		}
		return "delete";
	}
	
	@RequestMapping(value = "/car/update/{id}", method = RequestMethod.GET)
	private String updateCar(@PathVariable(value = "id") long id, Model model) {
		CarModel car = carService.getCar(id);
		model.addAttribute("car",car);
		return "changeCar";
	}
	
	@RequestMapping(value = "/car/update/{id}", method = RequestMethod.POST)
	private String updateCarSubmit(@PathVariable(value = "id") long id, @ModelAttribute CarModel car) {
		carService.updateCar(id, car);
		return "update";
	}
	
	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params= {"addRow"})
	public String addRow(@ModelAttribute DealerModel dealer, BindingResult bindingResult, Model model) {
		if (dealer.getListCar() == null) {
            dealer.setListCar(new ArrayList<CarModel>());
        }
		dealer.getListCar().add(new CarModel());
		
		model.addAttribute("dealer", dealer);
		return "addCar";
	}
	
	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params={"removeRow"})
	public String removeRow(@ModelAttribute DealerModel dealer, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
	    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	    dealer.getListCar().remove(rowId.intValue());
	    
	    model.addAttribute("dealer", dealer);
	    return "addCar";
	}*/
}
