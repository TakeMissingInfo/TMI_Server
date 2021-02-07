package com.takemissinghome.cafeteria.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import static java.lang.Math.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double calculateDistance(double latitude, double longitude) {
        double theta = this.longitude - longitude;
        double dist = (sin(convertDegToRad(this.latitude)) * sin(convertDegToRad(latitude)))
                + (cos(convertDegToRad(this.latitude)) * cos(convertDegToRad(latitude))
                * cos(convertDegToRad(theta)));

        dist = acos(dist);
        dist = convertRadToDeg(dist);
        dist *= (60 * 1.1515);

        final double kilometer = convertKilometer(dist);
        return convertDecimalPointFirst(kilometer);
    }

    private double convertDegToRad(double deg) {
        return (deg * PI / 180.0);
    }

    private double convertRadToDeg(double rad) {
        return (rad * 180 / PI);
    }

    private double convertKilometer(double dist) {
        return dist * 1.609344;
    }

    private double convertDecimalPointFirst(double kilometer) {
        return ceil((kilometer * 10)) / 10.0;
    }
}
