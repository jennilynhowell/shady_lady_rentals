package com.jennilyn.api_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//built this based off instructions at https://spring.io/guides/gs/consuming-rest/

@JsonIgnoreProperties(ignoreUnknown = true)
public class Periods {

    @JsonProperty("name")
    private String name;

    private int temperature;
    private String windSpeed;
    private String windDirection;
    private String shortForecast;
    private String icon;

    public Periods() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "name='" + name + '\'' +
                ", temperature=" + temperature +
                ", icon='" + icon + '\'' +
                ", shortForecast='" + shortForecast + '\'' +
                '}';
    }
}
