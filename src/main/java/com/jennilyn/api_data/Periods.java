package com.jennilyn.api_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//built this based off instructions at https://spring.io/guides/gs/consuming-rest/

@JsonIgnoreProperties(ignoreUnknown = true)
public class Periods {

    @JsonProperty("name")
    private String time;

    private int temperature;
    private String shortForecast;

    @JsonProperty("icon")
    private String iconUrl;

    public Periods() {}

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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
                "time='" + time + '\'' +
                ", temperature=" + temperature +
                ", iconUrl='" + iconUrl + '\'' +
                ", shortForecast='" + shortForecast + '\'' +
                '}';
    }
}
