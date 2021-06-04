package cst438.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "calendar")
public class Calendar {
	
	@Id
	private int id;
	private int car_id;
	private String date;
	private int reservation_id;
	
	public Calendar() {}

	public Calendar(int id, int car_id, String date, int reservation_id) {
		super();
		this.id = id;
		this.car_id = car_id;
		this.date = date;
		this.reservation_id = reservation_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCar_id() {
		return car_id;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}



	
}