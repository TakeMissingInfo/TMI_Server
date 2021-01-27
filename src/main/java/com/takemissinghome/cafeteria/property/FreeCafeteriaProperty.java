package com.takemissinghome.cafeteria.property;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cafeteria")
@Setter
public class FreeCafeteriaProperty {

    private String url;
    private String key;

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }
}
