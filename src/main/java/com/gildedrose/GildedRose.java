package com.gildedrose;

import com.gildedrose.model.*;
import com.gildedrose.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class GildedRose {

    private final Item[] items;
    private final Map<String, UpdatableItem> itemMap = new LinkedHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(GildedRose.class);

    public GildedRose(Item[] items) {
        this.items = items;
        logger.info("Initializing GildedRose with {} items", items.length);
        initializeItemMap();
    }

    private void initializeItemMap() {
        logger.debug("Initializing item map...");
        for (Item item : items) {
            String uniqueKey = generateUniqueKey(item);
            itemMap.put(uniqueKey, UpdatableItemFactory.createUpdatableItem(item));
            logger.debug("Added item to map: {}", uniqueKey);
        }
        logger.info("Item map initialized with {} items", itemMap.size());
    }

    private String generateUniqueKey(Item item) {
        String key = String.format("%s_%d_%d", item.name, item.sellIn, item.quality);
        logger.trace("Generated unique key: {}", key);
        return key;
    }

    public void updateQuality() {
        logger.info("Updating quality of items...");
        for (UpdatableItem updatableItem : itemMap.values()) {
            logger.debug("Updating item: {}", updatableItem);
            updatableItem.update();
        }
        logger.info("Quality update completed.");
    }

    public Item[] getItems() {
        return items;
    }
}
