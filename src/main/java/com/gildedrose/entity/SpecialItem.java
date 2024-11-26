package com.gildedrose.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SpecialItem {

    @Id
    private String name; // The name of the special item

    private String className; // The fully qualified class name of the special item

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
