package com.takemissinghome.config;

import com.takemissinghome.gov.property.OpenApiProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {OpenApiProperty.class})
public class PropertyConfig {
}
