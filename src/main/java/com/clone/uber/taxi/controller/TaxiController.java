package com.clone.uber.taxi.controller;

import com.clone.uber.driver.entity.Driver;
import com.clone.uber.taxi.entity.Taxi;
import com.clone.uber.taxi.service.TaxiService;
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
@RequestMapping("/taxi")
@RequiredArgsConstructor
public class TaxiController {

    private final TaxiService taxiService;

    @PostMapping("/add")
    public ResponseEntity<String> addTaxi(@RequestBody Taxi taxi) {
        taxiService.addTaxi(taxi);
        return new ResponseEntity<>("Taxi was successfully added!", HttpStatus.OK);
    }

    @GetMapping("/get/{taxiId}")
    public ResponseEntity<Taxi> getTaxi(@PathVariable Long taxiId) {
        try {
            Taxi taxi = taxiService.getTaxi(taxiId);
            return new ResponseEntity<>(taxi, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Taxi>> getAllTaxis() {
        List<Taxi> taxis = taxiService.getAllTaxis();

        if (taxis.isEmpty()) {
            log.info("There are no taxis!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(taxis, HttpStatus.FOUND);
        }
    }

    @DeleteMapping("/delete/{taxiId}")
    public ResponseEntity<String> deleteTaxi(@PathVariable Long taxiId) {
        taxiService.deleteById(taxiId);
        return new ResponseEntity<>("Taxi with id: [{}] was successfully deleted!",
                HttpStatus.OK); // TODO fix plus {}
    }

    @PostMapping("/{taxiId}/driver/{driverId}")
    public ResponseEntity<Taxi> addDriverForTaxi(@PathVariable Long taxiId, @PathVariable Long driverId) {
        return new ResponseEntity<>(taxiService.addDriverForTaxi(taxiId, driverId), HttpStatus.OK);
    }

    @PostMapping("/order/{userId}")
    public ResponseEntity<Taxi> orderTaxi(@PathVariable Long userId) {
        Taxi taxi = taxiService.orderTaxi(userId);
        if (taxi == null){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(taxi, HttpStatus.OK);
        }
    }
}
