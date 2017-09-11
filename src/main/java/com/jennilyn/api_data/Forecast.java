package com.jennilyn.api_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.access.method.P;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {

    @JsonProperty("properties")
    private Properties properties;

    public Forecast() {}

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
