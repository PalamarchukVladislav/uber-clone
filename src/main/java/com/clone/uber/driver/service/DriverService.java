package com.clone.uber.driver.service;

import com.clone.uber.driver.entity.Driver;
import com.clone.uber.driver.repository.DriverRepository;
import com.clone.uber.taxi.entity.Taxi;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public void addDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public Driver getDriver(Long driverId){
        return driverRepository.findById(driverId).get();
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    public void deleteById(Long driverId){
        driverRepository.deleteById(driverId);
    }

    public void updateDriver(Driver changedDriver) {
        Driver driverToUpdate = driverRepository.getOne(changedDriver.getId());

        driverToUpdate.setId(changedDriver.getId());
        driverToUpdate.setName(changedDriver.getName());
        driverToUpdate.setDriverMoney(changedDriver.getDriverMoney());
        driverToUpdate.setWorkingHours(changedDriver.getWorkingHours());
        driverToUpdate.setTaxi(changedDriver.getTaxi());
        driverToUpdate.setDriverLicenseNumber(changedDriver.getDriverLicenseNumber());

        driverRepository.save(changedDriver);
    }
}
