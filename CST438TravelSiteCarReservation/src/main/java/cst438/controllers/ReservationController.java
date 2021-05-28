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
import cst438.domain.Reservation;
import cst438.domain.ReservationRepository;

@Controller
public class ReservationController {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CarRepository carRepository;
	
	
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
		reservationRepository.save(reservation);
		carRepository.reserveCar(reservation.getCar_id());
		return "reservation_confirmed";
	}

}
