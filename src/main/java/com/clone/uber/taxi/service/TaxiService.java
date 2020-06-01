package com.clone.uber.taxi.service;

import com.clone.uber.driver.DriverStatus;
import com.clone.uber.driver.entity.Driver;
import com.clone.uber.driver.service.DriverService;
import com.clone.uber.taxi.entity.Taxi;
import com.clone.uber.taxi.repository.TaxiRepository;
import com.clone.uber.user.entity.User;
import com.clone.uber.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaxiService {

    private final TaxiRepository taxiRepository;
    private final DriverService driverService;
    private final UserService userService;

    public void addTaxi(Taxi taxi) {
        taxiRepository.save(taxi);
    }

    public Taxi getTaxi(Long taxiId) {
        return taxiRepository.findById(taxiId).get();
    }// TODO fix

    public List<Taxi> getAllTaxis() {
        return taxiRepository.findAll();
    }

    public void deleteById(Long taxiId) {
        taxiRepository.deleteById(taxiId);
    }

    public void updateTaxi(Taxi changedTaxi) {
        Taxi taxiToUpdate = taxiRepository.getOne(changedTaxi.getId());

        taxiToUpdate.setId(changedTaxi.getId());
        taxiToUpdate.setTaxiCar(changedTaxi.getTaxiCar());
        taxiToUpdate.setCarLicense(changedTaxi.getCarLicense());
        taxiToUpdate.setDriver(changedTaxi.getDriver());
        taxiToUpdate.setUser(changedTaxi.getUser());
        taxiRepository.save(taxiToUpdate);
    }

    public Taxi addDriverForTaxi(Long taxiId, Long driverId) {
        Driver driver = driverService.getDriver(driverId);
        Taxi taxi = getTaxi(taxiId);

        if (driver.getTaxi() == null) {
            taxi.setDriver(driver);
            updateTaxi(taxi);
        }
        return taxi;
    }

    public Taxi orderTaxi(Long userId) {
        Taxi taxi = getRandomTaxiWithDriver();
        if (taxi == null){
            return null;
        }
        taxi.getDriver().setDriverStatus(DriverStatus.BUSY);
        taxi.setUser(userService.getUser(userId));

        User user = userService.getUser(userId);
        user.setTaxi(taxi);

        Driver driver = taxi.getDriver();
        driver.setTaxi(taxi);

        updateTaxi(taxi);
        return taxi;
    }

    private Taxi getRandomTaxiWithDriver() {
        return getAllTaxis().stream()
                .filter(taxi -> taxi.getDriver().getDriverStatus().equals(DriverStatus.FREE))
                .findFirst().orElse(null);
    }
}
