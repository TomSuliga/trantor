package org.suliga.trantor.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cars")
public class TCar {
	@Id
	@GeneratedValue
	private Long id;
	
	private String make;
	
	private String model;
	
	private int year;
	
	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private TDriver driver;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public TDriver getDriver() {
		return driver;
	}

	public void setDriver(TDriver driver) {
		this.driver = driver;
	}
	
	@Override
	public String toString() {
		return "Car: " + make + " " + model + " " + year;
	}
}
