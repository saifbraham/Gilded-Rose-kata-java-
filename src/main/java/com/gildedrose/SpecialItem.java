package com.gildedrose;

public enum SpecialItem {
    AGED_BRIE("Aged Brie"),
    BACKSTAGE_PASS("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros");

    private final String itemName;

    SpecialItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
