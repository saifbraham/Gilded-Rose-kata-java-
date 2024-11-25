package com.gildedrose.model;

import com.gildedrose.model.items.StandardItem;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

public class UpdatableItemFactory {
    private static final Map<String, Function<Item, UpdatableItem>> ITEM_CREATORS = new HashMap<>();

    static {
        loadItemsFromConfig();
    }

    private static void loadItemsFromConfig() {
        try (InputStream input = UpdatableItemFactory.class.getClassLoader().getResourceAsStream("items.properties")) {
            if (input == null) {
                throw new RuntimeException("items.properties file not found in resources folder.");
            }

            Properties prop = new Properties();
            prop.load(input);

            for (String itemName : prop.stringPropertyNames()) {
                String className = prop.getProperty(itemName);
                registerItem(itemName, item -> instantiateItem(className, item));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load item configuration", e);
        }
    }

    private static UpdatableItem instantiateItem(String className, Item item) {
        try {
            Class<?> clazz = Class.forName(className);
            return (UpdatableItem) clazz.getConstructor(Item.class).newInstance(item);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create item instance for class: " + className, e);
        }
    }

    public static void registerItem(String itemName, Function<Item, UpdatableItem> constructor) {
        ITEM_CREATORS.put(itemName, constructor);
    }

    public static UpdatableItem createUpdatableItem(Item item) {
        // If item.name is not in the map, default to StandardItem constructor
        return ITEM_CREATORS.getOrDefault(item.name, StandardItem::new).apply(item);
    }
}
