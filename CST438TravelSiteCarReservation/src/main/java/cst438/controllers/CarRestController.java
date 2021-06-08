package cst438.controllers;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cst438.domain.*;
import cst438.repositories.*;
import cst438.services.*;

@RestController
public class CarRestController {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CarService CarService;
	
	@GetMapping("/carrental/api/carsByCity/{city}/{start_date}/{end_date}")
	public ResponseEntity<List <CarInfo>> carsByCity(@PathVariable("city")String city,@PathVariable("start_date")String start_date,@PathVariable("end_date")String end_date) throws ParseException{
		List<Car> cars = carRepository.findByCity(city);
		if(cars.size() == 0) {
			return new ResponseEntity <List <CarInfo>>( HttpStatus.NOT_FOUND);
			
		} else {
		List<CarInfo>carsAv = CarService.getCarAvList(city, start_date, end_date);
		
		return new ResponseEntity <List <CarInfo>>(carsAv, HttpStatus.OK);
		}
	}
	
	@GetMapping("/carrental/api/carsByCity/details/{id}")
	public ResponseEntity<CarInfo>carInfo(@PathVariable("id")int id) throws ParseException{
		if(id < 1 || id > 388) {
			return new ResponseEntity <CarInfo>( HttpStatus.NOT_FOUND);
			
		} else {
			CarInfo carInfo = CarService.getCarInfo(id);
		
		return new ResponseEntity <CarInfo>(carInfo, HttpStatus.OK);
		}
	}

}
