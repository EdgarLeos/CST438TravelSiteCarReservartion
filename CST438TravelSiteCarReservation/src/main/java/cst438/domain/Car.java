package cst438.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "car")
public class Car {
	
	@Id
	private int id;
	private String model;
	private String make;
	private int year;
	private String trany;
	private double cityMPG;
	private double highwayMPG;
	private String carClass;
	private String fuelType;
	private double rentalPrice;
	private String state;
	private String city;
	private int quantity;
	
	public Car() {}

	public Car(int id, String model, String make, int year, String trany, double cityMPG, double highwayMPG,
			String carClass, String fuelType, double rentalPrice, String state, String city, int quantity) {
		super();
		this.id = id;
		this.model = model;
		this.make = make;
		this.year = year;
		this.trany = trany;
		this.cityMPG = cityMPG;
		this.highwayMPG = highwayMPG;
		this.carClass = carClass;
		this.fuelType = fuelType;
		this.rentalPrice = rentalPrice;
		this.state = state;
		this.city = city;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTrany() {
		return trany;
	}

	public void setTrany(String trany) {
		this.trany = trany;
	}

	public double getCityMPG() {
		return cityMPG;
	}

	public void setCityMPG(double cityMPG) {
		this.cityMPG = cityMPG;
	}

	public double getHighwayMPG() {
		return highwayMPG;
	}

	public void setHighwayMPG(double highwayMPG) {
		this.highwayMPG = highwayMPG;
	}

	public String getCarClass() {
		return carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", make=" + make + ", year=" + year + ", trany=" + trany
				+ ", cityMPG=" + cityMPG + ", highwayMPG=" + highwayMPG + ", carClass=" + carClass + ", fuelType="
				+ fuelType + ", rentalPrice=" + rentalPrice + ", state=" + state + ", city=" + city + ", quantity="
				+ quantity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carClass == null) ? 0 : carClass.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cityMPG);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fuelType == null) ? 0 : fuelType.hashCode());
		temp = Double.doubleToLongBits(highwayMPG);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + quantity;
		temp = Double.doubleToLongBits(rentalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((trany == null) ? 0 : trany.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (carClass == null) {
			if (other.carClass != null)
				return false;
		} else if (!carClass.equals(other.carClass))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (Double.doubleToLongBits(cityMPG) != Double.doubleToLongBits(other.cityMPG))
			return false;
		if (fuelType == null) {
			if (other.fuelType != null)
				return false;
		} else if (!fuelType.equals(other.fuelType))
			return false;
		if (Double.doubleToLongBits(highwayMPG) != Double.doubleToLongBits(other.highwayMPG))
			return false;
		if (id != other.id)
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Double.doubleToLongBits(rentalPrice) != Double.doubleToLongBits(other.rentalPrice))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (trany == null) {
			if (other.trany != null)
				return false;
		} else if (!trany.equals(other.trany))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	

	
	

}