package com.takemissinghome.cafeteria.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cafeteria {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cafeteria_id")
    private Long id;

    @Column(name = "facility_name")
    private String facilityName;

    @Column(name = "address", unique = true)
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "operating_time")
    private String operatingTime;

    @Column(name = "operating_date")
    private String operatingDate;

    @Embedded
    private Location location;

    @Builder
    public Cafeteria(String facilityName, String address, String phoneNumber,
                     String operatingTime, String operatingDate, Location location) {
        this.facilityName = facilityName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.operatingTime = operatingTime;
        this.operatingDate = operatingDate;
        this.location = location;
    }

    public double calculateDistance(double latitude, double longitude){
        return this.location.calculateDistance(latitude, longitude);
    }
}
