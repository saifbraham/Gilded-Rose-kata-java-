package com.gildedrose.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table("SPECIAL_ITEM") // Flayway configuration needed
public class SpecialItem implements Comparable<SpecialItem>{

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

    @Override
    public int compareTo(SpecialItem other) {
        return this.name.compareTo(other.name); // Sort by name (or any other field)
    }

    @Override
    public String toString() {
        return "SpecialItem{name='" + name + "', className='" + className + "'}";
    }
}
