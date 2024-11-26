package com.gildedrose.factory;

import com.gildedrose.model.Item;

public class ItemBuilder {
    private String name;
    private int sellIn;
    private int quality;

    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder withSellIn(int sellIn) {
        this.sellIn = sellIn;
        return this;
    }

    public ItemBuilder withQuality(int quality) {
        this.quality = quality;
        return this;
    }

    public Item build() {
        return new Item(name, sellIn, quality);
    }
}
