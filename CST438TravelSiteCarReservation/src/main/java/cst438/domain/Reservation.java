package cst438.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Id;

@Entity
@Table(name = "reservations")
public class Reservation {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@Size(min = 3, max =45)
	private String email;
	
	private int car_id;
	
	public Reservation() {}

	public Reservation(int id, String email, int car_id) {
		super();
		this.id = id;
		this.email = email;
		this.car_id = car_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCar_id() {
		return car_id;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	
	

}
