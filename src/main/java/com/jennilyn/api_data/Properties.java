package com.jennilyn.api_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {

    @JsonProperty("periods")
    private List<Periods> periods;

    public Properties() {}

    public List<Periods> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Periods> periods) {
        this.periods = periods;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "periods=" + periods +
                '}';
    }
}
