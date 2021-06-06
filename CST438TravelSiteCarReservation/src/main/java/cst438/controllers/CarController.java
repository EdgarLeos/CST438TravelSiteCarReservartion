package cst438.controllers;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import cst438.domain.Car;
import cst438.repositories.CarRepository;

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
	@GetMapping("/randomFiveCars")
	public String geFiveRandomCars(Model model) {
		List<Car> randCars = carRepository.randomFive();
		/*for(Car car:cars) {
			System.out.println(car.getModel());
		}*/

		model.addAttribute("car", randCars);
		
		return "cars_info_five";
		
	}
	
	@GetMapping("/randomFiveCars/{id}")
	public String displayCarDetails(@PathVariable("id") int id, Model model) {
		System.out.println(id);
		//List<Car> randCars = carRepository.randomFive();
		/*for(Car car:cars) {
			System.out.println(car.getModel());
		}*/
		List<Car> cars = carRepository.findById(id);
		for(Car car:cars) {
			System.out.println(car.getId() + car.getModel());
		}
		Car car = cars.get(0);
		model.addAttribute("car", car);
		
		return "car_info";
		
	}

}