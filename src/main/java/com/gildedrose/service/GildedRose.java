package com.gildedrose.service;

import com.gildedrose.config.ItemsConfig;
import com.gildedrose.entity.SpecialItem;
import com.gildedrose.model.*;
import com.gildedrose.model.Item;
import com.gildedrose.factory.UpdatableItemFactory;
import com.gildedrose.repository.SpecialItemRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class GildedRose {

    private Item[] items;
    private final Map<String, UpdatableItem> itemMap = new LinkedHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(GildedRose.class);

    private final ItemsConfig itemsConfig;
    private final UpdatableItemFactory updatableItemFactory;
    private final SpecialItemRepository specialItemRepository;

    @Autowired
    public GildedRose(ItemsConfig itemsConfig, UpdatableItemFactory updatableItemFactory,
                      SpecialItemRepository specialItemRepository) {
        this.itemsConfig = itemsConfig;
        this.updatableItemFactory = updatableItemFactory;
        this.specialItemRepository = specialItemRepository;
    }

    @PostConstruct
    private void initializeSpecialItems() {
        String initializeSource = itemsConfig.getInitializeSource(); // Read from config

        if ("config".equalsIgnoreCase(initializeSource)) {
            logger.info("Initializing special items from configuration...");
            initializeSpecialItemsByConfig();
        } else if ("db".equalsIgnoreCase(initializeSource)) {
            logger.info("Initializing special items from database...");
            initializeSpecialItemsByDB();
        } else {
            throw new IllegalArgumentException("Invalid initialize-source: " + initializeSource);
        }
    }

    private void initializeSpecialItemsByConfig() {
        Map<String, String> specialItems = itemsConfig.getSpecialItems();
        if (specialItems != null) {
            specialItems.forEach(updatableItemFactory::registerItem);
            logger.info("Special items initialized from configuration: {}", specialItems.keySet());
        }
    }

    private void initializeSpecialItemsByDB() {
        List<SpecialItem> specialItems = specialItemRepository.findAll();
        if (specialItems.isEmpty()) {
            logger.warn("No special items found in the database!");
        } else {
            specialItems.forEach(item -> updatableItemFactory.registerItem(item.getName().replaceAll("[^a-zA-Z0-9]", ""), item.getClassName()));
            logger.info("Special items initialized from database: {}",
                specialItems.stream().map(SpecialItem::getName).toList());
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

    public void updateQuality() {
        logger.debug("Updating quality of items...");
        for (UpdatableItem updatableItem : itemMap.values()) {
            updatableItem.update();
        }
        logger.debug("Quality update completed.");
    }

    private String generateUniqueKey(Item item) {
        String key = String.format("%s_%s", item.name, UUID.randomUUID());
        logger.trace("Generated unique key: {}", key);
        return key;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
