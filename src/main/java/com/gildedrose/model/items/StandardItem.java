package com.gildedrose.model.items;

import com.gildedrose.model.AbstractItem;
import com.gildedrose.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardItem extends AbstractItem {

    private static final Logger logger = LoggerFactory.getLogger(StandardItem.class);

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
        logger.info("Name: {}, SellIn: {}, Quality: {}", item.name, item.sellIn, item.quality);
    }
}
