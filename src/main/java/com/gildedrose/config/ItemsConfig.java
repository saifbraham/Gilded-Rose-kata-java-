package com.gildedrose.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "items")
public class ItemsConfig {

    private Map<String, String> specialItems = new HashMap<>(); // Default to empty map

    public Map<String, String> getSpecialItems() {
        return specialItems;
    }

    public void setSpecialItems(Map<String, String> specialItems) {
        this.specialItems = specialItems;
    }
}


