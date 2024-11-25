package com.gildedrose.model;

public class StandardItem extends AbstractItem {
    public StandardItem(Item item) {
        super(item);
    }

    @Override
    public void update() {
        decreaseQuality();
        decreaseSellIn();
        if (item.sellIn < 0) {
            decreaseQuality(); // Quality decreases further after sell date
        }
    }
}
