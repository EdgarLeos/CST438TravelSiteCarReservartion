package cst438.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.text.ParseException;
import java.util.ArrayList;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import cst438.domain.*;
import cst438.repositories.*;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public CarInfo getCarInfo(int id) {
		List<Car> cars = carRepository.findById(id);
		Car car = cars.get(0);
		double countyTax = 0.02;
		double govFee = .1;
		double salesTax = 0.09;
		double plusCountyTax = (countyTax * car.getRentalPrice());
		double plusGovFee = (govFee * car.getRentalPrice());
		double plusSalesTax = (salesTax * car.getRentalPrice());
		double total = plusCountyTax + plusGovFee + plusSalesTax + car.getRentalPrice();
		
		return new CarInfo(car.getId(), car.getModel(), 
				car.getMake(), car.getYear(),car.getTrany(),car.getCityMPG(),
				car.getHighwayMPG(), car.getCarClass(),car.getFuelType(),
				car.getRentalPrice(), car.getState(), car.getCity(), car.getQuantity(), car.getImage() , plusCountyTax, plusGovFee, plusSalesTax,total);
	}
	
	public List<CarInfo> getCarAvList(String city, String start, String end) throws ParseException{
		
		List<Car> cars = carRepository.findByCity(city);
		List<CarInfo> carInfos = new ArrayList<>();
		
		for(Car car:cars) {
			carInfos.add(getCarInfo(car.getId()));
		}
		
		List<CarInfo> carsAv = new ArrayList<>();
		for(CarInfo car1:carInfos) {
			
			List<Reservation> reservations = reservationRepository.findDateRanges(start, end, car1.getId());
			if(reservations.size() >= car1.getQuantity()) {
				//DO NOTHING
			}else {
				carsAv.add(car1);
			}
		}
		
		return carsAv;
	}

}
