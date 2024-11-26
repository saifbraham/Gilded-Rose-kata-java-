package com.gildedrose.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "gildedrose")
public class ItemsConfig {

    private String initializeSource; // Source for initialization (config or db)
    private Map<String, String> specialItems = new HashMap<>(); // Default to empty map
    private int simulationDays;

    public int getSimulationDays() {
        return simulationDays;
    }

    public void setSimulationDays(int simulationDays) {
        this.simulationDays = simulationDays;
    }

    public String getInitializeSource() {
        return initializeSource;
    }

    public void setInitializeSource(String initializeSource) {
        this.initializeSource = initializeSource;
    }

    public Map<String, String> getSpecialItems() {
        return specialItems;
    }

    public void setSpecialItems(Map<String, String> specialItems) {
        this.specialItems = specialItems;
    }
}


