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
import cst438.domain.*;
import cst438.repositories.*;


@Controller
public class CarController {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CalendarRepository calendarRepository;
	
	@GetMapping("/carrental")
	public String displayCities(Model model) {
		Reservation resevation = new Reservation();
		Car car = new Car();
		Calendar calendar = new Calendar();
		model.addAttribute("reservation", resevation);
		model.addAttribute("car", car);
		model.addAttribute("calendar", calendar);
		return "car_rental";
	}
	
	
	@PostMapping("/carrental/carsByCity")
	public String displayCarsByCity(@RequestParam("city")String city,@RequestParam("start")String start,@RequestParam("end")String end, Model model) {
		System.out.println(city);
		System.out.println(start);
		System.out.println(end);
		//List<Car> randCars = carRepository.randomFive();
		/*for(Car car:cars) {
			System.out.println(car.getModel());
		}*/
		/*
		 * 
		 * */
		List<Car> cars = carRepository.findByCity(city);
		//System.out.println(Integer.toString(calendarRepository.findCarAvilable( date,4).get(0).getCar_id()));
		
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
		
		Reservation reservation = new Reservation();
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
		model.addAttribute("reservation" , reservation);
		
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