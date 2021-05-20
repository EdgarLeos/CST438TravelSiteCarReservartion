package cst438.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Integer>{
	
	@Query("select c from Car c order by model")
	List<Car> findByModel();

}
