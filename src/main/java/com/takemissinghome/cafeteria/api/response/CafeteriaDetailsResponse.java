package com.takemissinghome.cafeteria.api.response;

import com.takemissinghome.cafeteria.model.Cafeteria;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CafeteriaDetailsResponse {

    private String facilityName;
    private String address;
    private String phoneNumber;
    private String operatingTime;
    private String operatingDate;
    private double latitude;
    private double longitude;

    public CafeteriaDetailsResponse(Cafeteria cafeteria) {
        this.facilityName = cafeteria.getFacilityName();
        this.address = cafeteria.getAddress();
        this.phoneNumber = cafeteria.getPhoneNumber();
        this.operatingTime = cafeteria.getOperatingTime();
        this.operatingDate = cafeteria.getOperatingDate();
        this.latitude = cafeteria.getLocation().getLatitude();
        this.longitude = cafeteria.getLocation().getLongitude();
    }
}
