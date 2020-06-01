package com.clone.uber.taxi.entity;

import com.clone.uber.driver.entity.Driver;
import com.clone.uber.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Table(name = "taxis")
@Getter
@Setter
@NoArgsConstructor
public class Taxi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "taxi_car") // driverCar = taxiCar
    private String taxiCar; // consider to use Car object

    @Column(name = "car_license")
    private String carLicense; // BX1111AM

    @OneToOne(mappedBy = "taxi")
    @JsonBackReference
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver")
    private Driver driver;
}
