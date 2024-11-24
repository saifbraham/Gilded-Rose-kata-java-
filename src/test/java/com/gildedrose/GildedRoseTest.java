package com.gildedrose;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GildedRoseTest {

    private GildedRose app;
    private Item[] items;

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
    @DisplayName("Check items name and order after update quality call")
    void shouldNotChangeItemNameWhenUpdatingQuality() {
        Item[] items = new Item[] {
            new Item("+5 Dexterity Vest", 0, 0),
            new Item("Aged Brie", 0, 0),
            new Item("Elixir of the Mongoose", 0, 0),
            new Item("Sulfuras, Hand of Ragnaros", 0, 0),
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 0),
            new Item("Conjured Mana Cake", 0, 0)
        };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat("+5 Dexterity Vest").isEqualTo(app.getItems()[0].getName());
        assertThat("Aged Brie").isEqualTo(app.getItems()[1].getName());
        assertThat("Elixir of the Mongoose").isEqualTo(app.getItems()[2].getName());
        assertThat("Sulfuras, Hand of Ragnaros").isEqualTo(app.getItems()[3].getName());
        assertThat("Backstage passes to a TAFKAL80ETC concert").isEqualTo(app.getItems()[4].getName());
        assertThat("Conjured Mana Cake").isEqualTo(app.getItems()[5].getName());
    }

    @Test
    @DisplayName("Check update quality items after 10 days")
    void givenMultipleDays_whenUpdateQualityIsCalled_thenItemsAreUpdatedCorrectly(){

        // Arrange

        // 6 items in setUp() method
        int days = 10;

        // Act

        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }

        // Assert

        // Expected item names after 10 days
        String[] expectedNames = {
            "+5 Dexterity Vest",
            "Aged Brie",
            "Elixir of the Mongoose",
            "Sulfuras, Hand of Ragnaros",
            "Backstage passes to a TAFKAL80ETC concert",
            "Conjured Mana Cake"
        };

        // Expected sellIn values after 10 days
        int[] expectedSellIn = {
            0,  // +5 Dexterity Vest
            -8, // Aged Brie
            -5, // Elixir of the Mongoose
            0,  // Sulfuras, Hand of Ragnaros
            5,  // Backstage passes to a TAFKAL80ETC concert
            -7  // Conjured Mana Cake
        };

        // Expected quality values after 10 days
        int[] expectedQuality = {
            10, // +5 Dexterity Vest
            18, // Aged Brie
            0,  // Elixir of the Mongoose
            80, // Sulfuras, Hand of Ragnaros
            35, // Backstage passes to a TAFKAL80ETC concert
            0   // Conjured Mana Cake
        };

        // Initialize SoftAssertions
        SoftAssertions softly = new SoftAssertions();

        // Iterate over each item and assert its properties
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            softly.assertThat(item.getName())
                .as("Item %d: Name", i+1)
                .isEqualTo(expectedNames[i]);
            softly.assertThat(item.getSellIn())
                .as("Item %d: SellIn", i+1)
                .isEqualTo(expectedSellIn[i]);
            softly.assertThat(item.getQuality())
                .as("Item %d: Quality", i+1)
                .isEqualTo(expectedQuality[i]);
        }

        // Collect and report all assertion errors
        softly.assertAll();
    }


}
