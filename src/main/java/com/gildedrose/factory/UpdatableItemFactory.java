package com.gildedrose.factory;

import com.gildedrose.model.Item;
import com.gildedrose.model.UpdatableItem;
import com.gildedrose.model.items.StandardItem;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class UpdatableItemFactory {

    private final Map<String, Function<Item, UpdatableItem>> ITEM_CREATORS = new HashMap<>();

    /**
     * Registers an item by its class name. The item is instantiated lazily when needed.
     *
     * @param itemName  the name of the item
     * @param className the fully qualified class name of the UpdatableItem
     */
    public void registerItem(String itemName, String className) {
        ITEM_CREATORS.put(itemName, item -> instantiateItem(className, item));
    }

    /**
     * Creates an UpdatableItem instance for a given Item.
     *
     * @param item the Item to be wrapped in an UpdatableItem
     * @return the corresponding UpdatableItem or a default StandardItem
     */
    public UpdatableItem createUpdatableItem(Item item) {
        // If item.name is not in the map, default to StandardItem constructor
        return ITEM_CREATORS.getOrDefault(item.name.replaceAll("[^a-zA-Z0-9]", ""), StandardItem::new).apply(item);
    }

    /**
     * Instantiates an UpdatableItem using reflection.
     *
     * @param className the fully qualified class name of the UpdatableItem
     * @param item      the item to pass to the UpdatableItem constructor
     * @return the UpdatableItem instance
     */
    private UpdatableItem instantiateItem(String className, Item item) {
        try {
            Class<?> clazz = Class.forName(className);
            return (UpdatableItem) clazz.getConstructor(Item.class).newInstance(item);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create item instance for class: " + className, e);
        }
    }
}
