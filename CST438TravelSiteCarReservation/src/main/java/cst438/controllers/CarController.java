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
import cst438.domain.CarRepository;

@Controller
public class CarController {
	
	@Autowired
	CarRepository carRepository;
	
	
	@GetMapping("/carrental")
	public String displayCities(Model model) {
		Car car = new Car();
		model.addAttribute("car", car);
		return "car_rental";
	}
	
	
	@PostMapping("/carrental/carsByCity")
	public String displayCarsByCity(@RequestParam("city")String city, Model model) {
		System.out.println(city);
		//List<Car> randCars = carRepository.randomFive();
		/*for(Car car:cars) {
			System.out.println(car.getModel());
		}*/
		List<Car> cars = carRepository.findByCity(city);
		for(Car car:cars) {
			System.out.println(car.getId() + car.getModel());
		}
		model.addAttribute("car", cars);
		return "car_by_city";
		
	}
	
	@PostMapping("/carrental/carsByCity/reserved")
	public String carReserved(@RequestParam("id") int id, Model model) {
		System.out.println(id);
		//List<Car> randCars = carRepository.randomFive();
		/*for(Car car:cars) {
			System.out.println(car.getModel());
		}*/
		List<Car> cars = carRepository.findById(id);
		Car car = cars.get(0);
		double countyTax = 0.02;
		double govFee = .1;
		double salesTax = 0.09;
		double plusCountyTax = (countyTax * car.getRentalPrice());
		double plusGovFee= (govFee * car.getRentalPrice());
		double plusSalesTax = (salesTax * car.getRentalPrice());
		double total = plusCountyTax + plusGovFee + plusSalesTax + car.getRentalPrice();
		model.addAttribute("car", car);
		model.addAttribute("plusCountyTax", plusCountyTax);
		model.addAttribute("plusGovFee", plusGovFee);
		model.addAttribute("plusSalesTax", plusSalesTax);
		model.addAttribute("total", total);
		
		return "car_checkout";
	}
	
	
	@GetMapping("/carrental/carsByCity/details/{id}")
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
	@GetMapping("/allCars")
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
}