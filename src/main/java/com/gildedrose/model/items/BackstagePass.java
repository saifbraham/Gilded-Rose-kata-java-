package com.gildedrose.model.items;

import com.gildedrose.model.AbstractItem;
import com.gildedrose.model.Item;

public class BackstagePass extends AbstractItem {
    public BackstagePass(Item item) {
        super(item);
    }

    @Override
    public void update() {
        if (item.sellIn > 0) {
            increaseQuality();
            if (item.sellIn <= 10) {
                increaseQuality();
            }
            if (item.sellIn <= 5) {
                increaseQuality();
            }
        } else {
            item.quality = MIN_QUALITY; // Quality drops to 0 after the concert
        }
        decreaseSellIn();
    }
}
