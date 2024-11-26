package com.gildedrose.model.items;

import com.gildedrose.model.AbstractItem;
import com.gildedrose.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgedBrie extends AbstractItem {

    private static final Logger logger = LoggerFactory.getLogger(AgedBrie.class);

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
        logger.debug("{}, {}, {} ", item.name, item.sellIn, item.quality);
    }
}
