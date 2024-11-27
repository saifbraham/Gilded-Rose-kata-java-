package com.gildedrose.model.items;

import com.gildedrose.model.AbstractItem;
import com.gildedrose.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conjured extends AbstractItem {

    private static final Logger logger = LoggerFactory.getLogger(AgedBrie.class);

    public Conjured(Item item) {
        super(item);
    }

    @Override
    public void update() {
        decreaseQualityTwice();
        decreaseSellIn();
        if (item.sellIn < 0) {
            decreaseQualityTwice();
        }
        logger.info("Name: {}, SellIn: {}, Quality: {}", item.name, item.sellIn, item.quality);
    }

    private void decreaseQualityTwice() {
        decreaseQuality();
        decreaseQuality();
    }
}
