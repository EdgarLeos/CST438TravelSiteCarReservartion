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
public class ReservationController {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CalendarRepository calendarRepository;
	
	@GetMapping("/allReservations")
	public String getAllReservations(Model model) {
		List<Reservation> reservations = reservationRepository.findAllReservations();
		
		model.addAttribute("reservations", reservations);
		
		return "reservation_confirmed";
	}
	
	@PostMapping("/carrental/carsByCity/reserved/checkout")
	public String processCarRental(@Valid Reservation reservation, BindingResult result, Model model) {
		if(result.hasErrors()) {
			System.out.println("Error!!!!");
			return "car_checkout";
		}
		System.out.println(reservation.getDate_start());
		reservationRepository.save(reservation);
		//carRepository.reserveCar(reservation.getCar_id());
		//calendarRepository.addReservationToCalendar(reservation.getCar_id(),"2020-07-06", reservation.getId());
		model.addAttribute("reservation", reservation);
		return "reservation_confirmed";
	}
	
	@PostMapping("/carrental/checkReservation")
	public String viewCarRental(@Valid Reservation reservation, @RequestParam("email") String email,@RequestParam("id") int id,  BindingResult result, Model model) {

		if(result.hasErrors()) {
			System.out.println("Error!!!!");
			return "car_checkout";
		}
	
		List<Reservation> reservations = reservationRepository.findById(id);

		if(reservations == null) 
			return "resevation_Not_Found";	
		
		Reservation reservation1 = reservations.get(0);

		if(!(reservation1.getEmail().equals(email)))
				return "wrong_Credentials";
			
		List<Car> cars = carRepository.findById(reservation1.getCar_id());
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
		model.addAttribute("reservation" , reservation1);
		return "reservation_details";
	}
	
	@GetMapping("/carrental/deleteReservation/{id}")
	public String deleteReservation(@PathVariable("id") int id, Model model) {
		List<Reservation> reservations = reservationRepository.findById(id);
		Reservation reservation = reservations.get(0);
		//carRepository.cancelCar(reservation.getCar_id());
		reservationRepository.cancelReservation(id);
		return "reservation_deleted";
	}
}
