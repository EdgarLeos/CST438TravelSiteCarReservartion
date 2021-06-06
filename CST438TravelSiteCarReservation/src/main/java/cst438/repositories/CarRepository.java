package cst438.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cst438.domain.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{
	
	
	
	@Query("SELECT c FROM Car c ORDER BY model")
	List<Car> findByModel();
	
	@Query(nativeQuery = true, value = "SELECT * FROM car ORDER BY RAND() LIMIT 5")
	List<Car> randomFive();
	
	List<Car> findById(int id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM car  WHERE car.city LIKE ?1 AND car.quantity > 0")
	List<Car> findByCity(String city);
	

	@Transactional
	@Modifying
	@Query("UPDATE Car c SET c.quantity = c.quantity - 1 WHERE c.id = ?1")
	void reserveCar(int id);
	
	@Transactional
	@Modifying
	@Query("UPDATE Car c SET c.quantity = c.quantity + 1 WHERE c.id = ?1")
	void cancelCar(int id);
}