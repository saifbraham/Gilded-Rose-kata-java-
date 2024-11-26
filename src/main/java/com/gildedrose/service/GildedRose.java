package com.gildedrose.service;

import com.gildedrose.config.ItemsConfig;
import com.gildedrose.model.*;
import com.gildedrose.model.Item;
import com.gildedrose.factory.UpdatableItemFactory;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GildedRose {

    private Item[] items;
    private final Map<String, UpdatableItem> itemMap = new LinkedHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(GildedRose.class);

    private final ItemsConfig itemsConfig;
    private final UpdatableItemFactory updatableItemFactory;

    @Autowired
    public GildedRose(ItemsConfig itemsConfig, UpdatableItemFactory updatableItemFactory) {
        this.itemsConfig = itemsConfig;
        this.updatableItemFactory = updatableItemFactory;
    }

    @PostConstruct
    private void initializeSpecialItems() {
        // Register special items from configuration
        Map<String, String> specialItems = itemsConfig.getSpecialItems();
        if (specialItems != null) {
            specialItems.forEach(updatableItemFactory::registerItem);
        }
    }

    public void initialize(Item[] items) {
        this.items = items;
        logger.warn("Initializing GildedRose with {} items", items.length);
        initializeItemMap();
    }

    private void initializeItemMap() {
        logger.warn("Initializing item map...");
        for (Item item : items) {
            String uniqueKey = generateUniqueKey(item);
            itemMap.put(uniqueKey, updatableItemFactory.createUpdatableItem(item));
            logger.debug("Added item to map: {}", uniqueKey);
        }
        logger.debug("Item map initialized with {} items", itemMap.size());
    }

    private String generateUniqueKey(Item item) {
        String key = String.format("%s_%s", item.name, UUID.randomUUID());
        logger.trace("Generated unique key: {}", key);
        return key;
    }

    public void updateQuality() {
        logger.debug("Updating quality of items...");
        for (UpdatableItem updatableItem : itemMap.values()) {
            updatableItem.update();
        }
        logger.debug("Quality update completed.");
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
