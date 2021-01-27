package com.takemissinghome.gov.property;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openapi")
@Setter
public class OpenApiProperty {

    private String url;
    private String key;

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }
}
