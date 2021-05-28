package cst438.domain;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
	@Query("SELECT r FROM Reservation r ORDER BY id")
	List<Reservation>findAllReservations();
	
	List<Reservation>findById(int id);
	

}
