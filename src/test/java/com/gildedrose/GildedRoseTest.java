package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GildedRoseTest {

    private GildedRose app;
    private Item[] items;
    public static final int DAYS =10;

    @BeforeEach
    void setUp() {
        items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Conjured Mana Cake", 3, 6)
        };
        app = new GildedRose(items);
    }

    @Test
    void shouldNotChangeItemNameWhenUpdatingQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat("Aged Brie").isEqualTo(app.getItems()[0].getName());
    }

    @Test
    void givenMultipleDays_whenUpdateQualityIsCalled_thenItemsAreUpdatedCorrectly(){

        // TODO
        // Avoid using System.out.println statements in test methods; instead, use assertions to validate the behavior.

        for (int i = 0; i < DAYS; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
    }

}
