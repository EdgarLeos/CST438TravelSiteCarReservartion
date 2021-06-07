package cst438.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import cst438.services.*;



@Controller
public class CarController {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CarService CarService;
	
	
	@GetMapping("/carrental")
	public String displayCities(Model model) {
		Reservation resevation = new Reservation();
		Car car = new Car();
		model.addAttribute("reservation", resevation);
		model.addAttribute("car", car);
		return "car_rental";
	}
	
	
	@PostMapping("/carrental/carsByCity")
	public String displayCarsByCity(@RequestParam("city")String city,@RequestParam("start")String start,@RequestParam("end")String end, Model model) throws ParseException {
//		System.out.println(city);
//		System.out.println(start);
//		System.out.println(end);
		//List<Car> randCars = carRepository.randomFive();
		/*for(Car car:cars) {
			System.out.println(car.getModel());
		}*/
		/*
		 * 
		 * */
		//List<Car> cars = carRepository.findByCity(city);
		//System.out.println(Integer.toString(calendarRepository.findCarAvilable( date,4).get(0).getCar_id()));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date date1 = format.parse(start);
		Date date2 = format.parse(end);
		
		String date_start;
		String date_end;
		if (!(date1.compareTo(date2) <= 0)) {
			date_start = end;
			date_end = start;
		}else {
			date_start = start;
			date_end = end;
		}
		
		List<CarInfo>carsAv = CarService.getCarAvList(city, date_start, date_end);
//		for(Car car:cars) {
//			System.out.println(car.getId());
//		}
//		List<Car> carsAv = new ArrayList<>();
//		for(Car car:cars) {
//			List<Reservation> reservations = reservationRepository.findDateRanges(start, end, car.getId());
//			if(reservations.size() >= car.getQuantity()) {
//				System.out.println("car will be removed from list");
//				System.out.println(car.getId());
//				System.out.println("car removed from list");
//			}else {
//				carsAv.add(car);
//			}
//		}
//		
//		for(Car car:carsAv) {
//			System.out.println(car.getId());
//		}
//		for(Reservation reservation: reservations) {
//			System.out.println(reservation.getCar_id());
//		}
		model.addAttribute("car", carsAv);
		model.addAttribute("date_start", date_start);
		model.addAttribute("date_end", date_end);
		return "car_by_city";
		
	}
	
	@PostMapping("/carrental/carsByCity/reserved")
	public String carReserved(@RequestParam("id") int id,@RequestParam("date_start") String date_start,@RequestParam("date_end") String date_end, Model model) {
		System.out.println(id);
		//List<Car> randCars = carRepository.randomFive();
		/*for(Car car:cars) {
			System.out.println(car.getModel());
		}*/
		CarInfo car = CarService.getCarInfo(id);
		Reservation reservation = new Reservation();
		reservation.setDate_start(date_start);
		reservation.setDate_end(date_end);
//		double countyTax = 0.02;
//		double govFee = .1;
//		double salesTax = 0.09;
//		double plusCountyTax = (countyTax * car.getRentalPrice());
//		double plusGovFee= (govFee * car.getRentalPrice());
//		double plusSalesTax = (salesTax * car.getRentalPrice());
//		double total = plusCountyTax + plusGovFee + plusSalesTax + car.getRentalPrice();
		
		model.addAttribute("car", car);
		model.addAttribute("reservation" , reservation);
		
		return "car_checkout";
	}
	
	
	@GetMapping("/carrental/carsByCity/details/{id}")
	public String displayCarDetails(@PathVariable("id") int id,  Model model) {
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