package com.takemissinghome.cafeteria.property;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "cafeteria")
@RequiredArgsConstructor
public class FreeCafeteriaProperty {

    private final String url;
    private final String key;

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }
}
