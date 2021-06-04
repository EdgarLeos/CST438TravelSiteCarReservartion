package cst438.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cst438.domain.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Integer>{
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO calendar (car_id, date, reservation_id) VALUES (?,?,?)")
	void addReservationToCalendar(int id, String date, int reservation_id);

}
