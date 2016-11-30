package org.suliga.trantor.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.suliga.trantor.model.Car;
import org.suliga.trantor.model.CarRepo;
import org.suliga.trantor.model.Driver;
import org.suliga.trantor.model.DriverRepo;

@Service
public class CarDriverService {
	@Autowired
	private CarRepo carRepo;
	    
	@Autowired
	private DriverRepo driverRepo;
	
	@Transactional
	public void add(Car car, Driver driver) {
		if (car == null || car.getMake() == null || driver == null || driver.getFirstName() == null) {
			return;
		}
		car.setDriver(driver);
		driver.setCar(car);
		carRepo.save(car);
		driverRepo.save(driver);
	}
	
	@Transactional
	public void remove(String carId, String driverId) {
		if (carId != null && carId.length() > 0) {
			Long id = Long.parseLong(carId);
			System.out.println("carId=" + id);
			carRepo.delete(id);
		}
		if (driverId != null && driverId.length() > 0) {
			Long id = Long.parseLong(driverId);
			System.out.println("driverId=" + id);
			driverRepo.delete(id);
		}
	}
}
