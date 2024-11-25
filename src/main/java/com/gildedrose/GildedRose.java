package com.gildedrose;

import com.gildedrose.model.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class GildedRose {
    private final Item[] items;
    private final Map<String, UpdatableItem> itemMap = new LinkedHashMap<>();

    public GildedRose(Item[] items) {
        this.items = items;
        initializeItemMap();
    }

    private void initializeItemMap() {
        for (Item item : items) {
            String uniqueKey = generateUniqueKey(item);
            itemMap.put(uniqueKey, UpdatableItemFactory.createUpdatableItem(item));
        }
    }

    private String generateUniqueKey(Item item) {
        return String.format("%s_%d_%d", item.name, item.sellIn, item.quality);
    }

    public void updateQuality() {
        for (UpdatableItem updatableItem : itemMap.values()) {
            updatableItem.update();
        }
    }

    public Item[] getItems() {
        return items;
    }
}
