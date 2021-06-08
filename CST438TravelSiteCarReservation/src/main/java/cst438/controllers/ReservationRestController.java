package cst438.controllers;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cst438.domain.*;
import cst438.repositories.*;
import cst438.services.*;

@RestController
public class ReservationRestController {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CarService CarService;
	
	@GetMapping("/carrental/api/reservations/{email}")
	public ResponseEntity<List <Reservation>> reservationsByEmail(@PathVariable("email")String email) throws ParseException{
		List<Reservation> reservations = reservationRepository.findByEmail(email);
		if(reservations.size() == 0) {
			return new ResponseEntity <List <Reservation>>( HttpStatus.NOT_FOUND);
			
		} else {
		
			return new ResponseEntity <List <Reservation>>(reservations, HttpStatus.OK);
		}
	}
	
	@GetMapping("/carrental/api/reservations/details/{id}")
	public ResponseEntity<Reservation>carInfo(@PathVariable("id")int id) throws ParseException{
		List<Reservation> reservations = reservationRepository.findById(id);
		if(reservations.size() == 0) {
			return new ResponseEntity <Reservation>( HttpStatus.NOT_FOUND);
			
		} else {
			Reservation reservation = reservations.get(0);
			return new ResponseEntity <Reservation>(reservation, HttpStatus.OK);
		}
	}
	
	@PostMapping(path = "/carrental/api/reserve", consumes = "application/json", produces = "application/json")
	public void addReservation(@RequestBody Reservation reservation) {

		reservationRepository.save(reservation);
	}

}
