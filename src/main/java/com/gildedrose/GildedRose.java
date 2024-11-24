package com.gildedrose;

class GildedRose {

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isStandardItem(item)) {
                handleStandardItem(item);
            } else if (isSpecialItem(item, SpecialItem.BACKSTAGE_PASS)) {
                handleBackstagePass(item);
            } else if (isSpecialItem(item, SpecialItem.AGED_BRIE)) {
                handleAgedBrie(item);
            }
        }
    }

    private boolean isSpecialItem(Item item, SpecialItem specialItem) {
        return specialItem.getItemName().equals(item.getName());
    }

    private boolean isStandardItem(Item item) {
        return !isSpecialItem(item, SpecialItem.AGED_BRIE) &&
            !isSpecialItem(item, SpecialItem.BACKSTAGE_PASS) &&
            !isSpecialItem(item, SpecialItem.SULFURAS);
    }

    private void increaseQuality(Item item) {
        if (item.getQuality() < MAX_QUALITY) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private void decreaseQuality(Item item) {
        if (item.getQuality() > MIN_QUALITY) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private void decreaseSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private void handleBackstagePass(Item item) {
        if (item.getSellIn() > 0) {
            increaseQuality(item);
            if (item.getSellIn() <= 10) {
                increaseQuality(item);
            }
            if (item.getSellIn() <= 5) {
                increaseQuality(item);
            }
        } else {
            item.setQuality(MIN_QUALITY); // Quality drops to 0 after the concert
        }
        decreaseSellIn(item);
    }

    private void handleAgedBrie(Item item) {
        increaseQuality(item);
        decreaseSellIn(item);
        if (item.getSellIn() < 0) {
            increaseQuality(item); // Aged Brie improves further after sell date
        }
    }

    private void handleStandardItem(Item item) {
        decreaseQuality(item);
        decreaseSellIn(item);
        if (item.getSellIn() < 0) {
            decreaseQuality(item); // Quality decreases further after sell date
        }
    }

}
