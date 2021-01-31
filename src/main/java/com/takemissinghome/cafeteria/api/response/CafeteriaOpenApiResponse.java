package com.takemissinghome.cafeteria.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@NoArgsConstructor
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class CafeteriaOpenApiResponse {

    @XmlElement(name = "body")
    private Body body;

    @Getter
    @NoArgsConstructor
    @XmlRootElement(name = "body")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Body {

        @XmlElementWrapper(name = "items")
        @XmlElement(name = "item")
        private List<Item> items;

        private int totalCount;
        private int numOfRows;
        private int pageNo;

        public List<Item> getItems() {
            return items;
        }
    }

    @Getter
    @NoArgsConstructor
    @XmlRootElement(name = "item")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Item {
        private String fcltyNm;
        private String rdnmadr;
        private String phoneNumber;
        private String mlsvPlace;
        private String mlsvDate;
        private double latitude;
        private double longitude;
    }
}