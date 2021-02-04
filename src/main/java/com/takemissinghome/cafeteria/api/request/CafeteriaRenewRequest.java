package com.takemissinghome.cafeteria.api.request;

import com.takemissinghome.cafeteria.api.response.CafeteriaOpenApiResponse.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CafeteriaRenewRequest {

    private String facilityName;
    private String address;
    private String phoneNumber;
    private String operatingTime;
    private String operatingDate;
    private double latitude;
    private double longitude;

    public CafeteriaRenewRequest(Item item) {
        this.facilityName = item.getFcltyNm();
        this.address = item.getRdnmadr();
        this.phoneNumber = item.getPhoneNumber();
        this.operatingTime = item.getMlsvTime();
        this.operatingDate = item.getMlsvDate();
        this.latitude = item.getLatitude();
        this.longitude = item.getLongitude();
    }
}
