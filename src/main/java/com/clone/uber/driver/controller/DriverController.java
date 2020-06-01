package com.clone.uber.driver.controller;

import com.clone.uber.driver.entity.Driver;
import com.clone.uber.driver.service.DriverService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/add")
    public ResponseEntity<String> addDriver(@RequestBody Driver driver) {
        driverService.addDriver(driver);
        return new ResponseEntity<>("Driver was successfully added!", HttpStatus.OK);
    }

    @GetMapping("/get/{driverId}")
    public ResponseEntity<Driver> getDriver(@PathVariable Long driverId) {
        try {
            Driver driver = driverService.getDriver(driverId);
            return new ResponseEntity<>(driver, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();

        if (drivers.isEmpty()) {
            log.info("There are no drivers!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(drivers, HttpStatus.FOUND);
        }
    }

    @DeleteMapping("/delete/{driverId}")
    public ResponseEntity<String> deleteDriver(@PathVariable Long driverId) {
        driverService.deleteById(driverId);
        return new ResponseEntity<>("Driver with id: [{}] was successfully deleted!",
                HttpStatus.OK); // TODO fix plus {}
    }

}
