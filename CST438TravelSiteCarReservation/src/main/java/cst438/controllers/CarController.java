package cst438.controllers;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cst438.domain.Car;
import cst438.domain.CarRepository;

@Controller
public class CarController {
	
	@Autowired
	CarRepository carRepository;
	
	@GetMapping("/carReservation")
	public String getAllCars(Model model) {
		List<Car> cars = carRepository.findByModel();
		/*for(Car car:cars) {
			System.out.println(car.getModel());
		}*/

		model.addAttribute("car", cars);
		
		return "cars_list";
		
	}

}
