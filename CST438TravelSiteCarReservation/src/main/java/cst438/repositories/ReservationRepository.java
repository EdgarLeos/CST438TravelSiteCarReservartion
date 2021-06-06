package cst438.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cst438.domain.Reservation;


public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
	@Query("SELECT r FROM Reservation r ORDER BY id")
	List<Reservation>findAllReservations();
	
	List<Reservation>findById(int id);
	
	List<Reservation>findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query("DELETE Reservation r WHERE r.id = ?1")
	void cancelReservation(int id);

}