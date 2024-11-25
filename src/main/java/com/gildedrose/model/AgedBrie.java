package com.gildedrose.model;

public class AgedBrie extends AbstractItem {
    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    public void update() {
        increaseQuality();
        decreaseSellIn();
        if (item.sellIn < 0) {
            increaseQuality(); // Aged Brie improves further after sell date
        }
    }
}
