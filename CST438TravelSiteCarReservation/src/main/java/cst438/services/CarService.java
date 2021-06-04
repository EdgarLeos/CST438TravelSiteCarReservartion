package cst438.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import cst438.domain.*;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public CarInfo getCarInfo(int id) {
		List<Car> cars = carRepository.findById(id);
		Car car = cars.get(0);
		return new CarInfo(car.getId(), car.getModel(), 
				car.getMake(), car.getYear(),car.getTrany(),car.getCityMPG(),
				car.getHighwayMPG(), car.getCarClass(),car.getFuelType(),
				car.getRentalPrice(), car.getState(), car.getCity(), car.getQuantity(), 0.02, .1, 0.09);
	}

}
