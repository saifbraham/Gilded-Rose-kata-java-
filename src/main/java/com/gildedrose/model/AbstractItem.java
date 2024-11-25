package com.gildedrose.model;

public abstract class AbstractItem implements UpdatableItem {
    protected static final int MAX_QUALITY = 50;
    protected static final int MIN_QUALITY = 0;
    protected final Item item;

    public AbstractItem(Item item) {
        this.item = item;
    }

    protected void increaseQuality() {
        if (item.quality < MAX_QUALITY) {
            item.quality++;
        }
    }

    protected void decreaseQuality() {
        if (item.quality > MIN_QUALITY) {
            item.quality--;
        }
    }

    protected void decreaseSellIn() {
        item.sellIn--;
    }
}
