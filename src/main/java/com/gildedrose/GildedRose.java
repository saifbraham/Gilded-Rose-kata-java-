package com.gildedrose;

class GildedRose {
    private Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private boolean isAgedBrie(Item item) {
        return "Aged Brie".equals(item.getName());
    }

    private boolean isBackstagePass(Item item) {
        return "Backstage passes to a TAFKAL80ETC concert".equals(item.getName());
    }

    private boolean isDexterityVest(Item item) {
        return "+5 Dexterity Vest".equals(item.getName());
    }

    private boolean isElixirMongoose(Item item) {
        return "Elixir of the Mongoose".equals(item.getName());
    }

    private void increaseQuality(Item item) {
        item.setQuality(item.getQuality() + 1);
    }

    private void decreaseQuality(Item item) {
        item.setQuality(item.getQuality() - 1);
    }

    private void decreaseSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private void handleBackstagePass(Item item) {
        if (item.getQuality() < 50) {
            increaseQuality(item);
        }
        if (item.getSellIn() < 11) {
            increaseQuality(item);
        }
        if (item.getSellIn() < 6) {
            increaseQuality(item);
        }

        if (item.getQuality() > 50) {
            item.setQuality(50);
        }
        decreaseSellIn(item);
        if (item.getSellIn() < 0) {
            item.setQuality(0);
        }
    }

    private void handleAgedBrie(Item item) {
        if (item.getQuality() < 50) {
            increaseQuality(item);
        }
        decreaseSellIn(item);
        if (item.getSellIn() < 0 && item.getQuality() < 50) {
            increaseQuality(item);
        }
    }

    private void handleDexterityVestElixirMongooseConjuredManaCake(Item item) {
        if (item.getQuality() > 0) {
            decreaseQuality(item);
        }
        decreaseSellIn(item);
        if (item.getSellIn() < 0 && item.getQuality() > 0) {
            decreaseQuality(item);
        }
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            if (isDexterityVest(items[i]) ||
                isElixirMongoose(items[i])) {
                handleDexterityVestElixirMongooseConjuredManaCake(items[i]);
            }

            if (isBackstagePass(items[i]))
                handleBackstagePass(items[i]);

            if (isAgedBrie(items[i]))
                handleAgedBrie(items[i]);

        }
    }

    public Item[] getItems() {
        return items;
    }

}
