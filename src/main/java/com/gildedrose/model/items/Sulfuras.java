package com.gildedrose.model.items;

import com.gildedrose.model.AbstractItem;
import com.gildedrose.model.Item;

public class Sulfuras extends AbstractItem {
    public Sulfuras(Item item) {
        super(item);
    }

    @Override
    public void update() {
        // Sulfuras does not change in quality or sell-in
    }
}
