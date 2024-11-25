package com.gildedrose.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class UpdatableItemFactory {
    private static final Map<String, Function<Item, UpdatableItem>> ITEM_CREATORS = new HashMap<>();

    static {
        ITEM_CREATORS.put("Aged Brie", AgedBrie::new);
        ITEM_CREATORS.put("Backstage passes to a TAFKAL80ETC concert", BackstagePass::new);
        ITEM_CREATORS.put("Sulfuras, Hand of Ragnaros", Sulfuras::new);
    }

    public static UpdatableItem createUpdatableItem(Item item) {
        // If item.name is not in the map, default to StandardItem constructor
        return ITEM_CREATORS.getOrDefault(item.name, StandardItem::new).apply(item);
    }
}
