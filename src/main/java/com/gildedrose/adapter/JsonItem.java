package com.gildedrose.adapter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gildedrose.model.Item;

public class JsonItem {

    private String name;
    private int sellIn;
    private int quality;

    @JsonCreator
    public JsonItem(
        @JsonProperty("name") String name,
        @JsonProperty("sellIn") int sellIn,
        @JsonProperty("quality") int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    // Convert to legacy Item
    public Item toLegacyItem() {
        return new Item(name, sellIn, quality);
    }

    // Convert from legacy Item
    public static JsonItem fromLegacyItem(Item item) {
        return new JsonItem(item.name, item.sellIn, item.quality);
    }
}
