package com.clone.uber.driver.entity;

import com.clone.uber.driver.DriverStatus;
import com.clone.uber.taxi.entity.Taxi;
import com.clone.uber.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Table(name = "drivers")
@Getter
@Setter
@NoArgsConstructor
public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "driver_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "driver_status")
    private DriverStatus driverStatus;

    @Column(name = "driver_money")
    private String driverMoney;

    @Column(name = "driver_license")
    private String driverLicenseNumber;

    @Column(name = "working_hours")
    private byte workingHours; // hours on the line(max 24), it is okay to use byte?

    @OneToOne(mappedBy = "driver")
    @JsonBackReference
    private Taxi taxi;
}
