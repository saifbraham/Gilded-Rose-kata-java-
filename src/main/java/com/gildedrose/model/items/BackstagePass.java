package com.gildedrose.model.items;

import com.gildedrose.model.AbstractItem;
import com.gildedrose.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackstagePass extends AbstractItem {

    private static final Logger logger = LoggerFactory.getLogger(BackstagePass.class);

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
        logger.info("Name: {}, SellIn: {}, Quality: {}", item.name, item.sellIn, item.quality);
    }
}
