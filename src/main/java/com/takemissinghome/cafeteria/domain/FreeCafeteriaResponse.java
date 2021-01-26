package com.takemissinghome.cafeteria.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FreeCafeteriaResponse {

    private Body body;

    @NoArgsConstructor
    @Getter
    private class Body {
        private Items items;
    }

    @NoArgsConstructor
    @Getter
    private class Items {
        private String fcltyNm;
        private String Inmadr;
        private String phoneNumber;
        private String mlsvPlace;
        private String mlsvDate;
        private String latitue;
        private String longitude;
    }
}