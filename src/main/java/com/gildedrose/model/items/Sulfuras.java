package com.gildedrose.model.items;

import com.gildedrose.model.AbstractItem;
import com.gildedrose.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sulfuras extends AbstractItem {

    private static final Logger logger = LoggerFactory.getLogger(Sulfuras.class);

    public Sulfuras(Item item) {
        super(item);
    }

    @Override
    public void update() {
        // Sulfuras does not change in quality or sell-in
        logger.info("Name: {}, SellIn: {}, Quality: {}", item.name, item.sellIn, item.quality);
    }
}
