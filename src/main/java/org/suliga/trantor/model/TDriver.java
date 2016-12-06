package org.suliga.trantor.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="drivers")
public class TDriver {
	@Id
	@GeneratedValue
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private TCar car;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public TCar getCar() {
		return car;
	}

	public void setCar(TCar car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Driver: " + firstName + " " + lastName;
	}
}
