package com.gildedrose;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GildedRoseTest {

    private GildedRose app;
    private Item[] items;

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
    void givenTenDays_whenUpdateQualityIsCalled_thenItemsAreUpdatedCorrectly(){

        // Arrange

        int days = 10;
        items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Conjured Mana Cake", 3, 6)
        };
        app = new GildedRose(items);

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

    @Test
    @DisplayName("Backstage pass item - quality becomes 0 if sellIn < 0")
    void givenBackstagePassItemWithDifferentValueOfSellIn_whenUpdateQualityIsCalledAndGetNegativeSellInValue_thenShouldSetQualityToZero(){

        // Arrange

        int days = 10;
        items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 45),
            new Item("Backstage passes to a TAFKAL80ETC concert", -1, 42),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 38),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 40),
            new Item("Backstage passes to a TAFKAL80ETC concert", 6, 35),
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 25),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)
        };
        app = new GildedRose(items);

        // Act

        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }

        // Assert


        // Expected sellIn values after 10 days
        int[] expectedSellIn = {-10, -9, -11, -5, 0, -4, 1, 5};

        // Expected quality values after 10 days
        int[] expectedQuality = {0, 0, 0, 0, 50, 0, 48, 35};

        // Initialize SoftAssertions
        SoftAssertions softly = new SoftAssertions();

        // Iterate over each item and assert its properties
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
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

    @Test
    @DisplayName("Backstage pass item - while sellIn > 10 then increment quality by 1 and max value is 50 ")
    void givenBackstagePassItemWithPositiveSellIn_whenUpdateQualityIsCalledAndWhileSellInSupToTen_thenShouldIncrementQualityByOneAndMaxValueIsFifty(){

        // Arrange

        int days = 10;
        items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 19, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 30, 42),
            new Item("Backstage passes to a TAFKAL80ETC concert", 18, 45),
            new Item("Backstage passes to a TAFKAL80ETC concert", 18, 35),
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 9, 35),
            new Item("Backstage passes to a TAFKAL80ETC concert", 19, 43)
        };
        app = new GildedRose(items);

        // Act

        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }

        // Assert

        // Expected sellIn values after 10 days
        int[] expectedSellIn = {9, 20, 8, 8, 1, -1, 9};

        // Expected quality values after 10 days
        int[] expectedQuality = {31, 50, 50, 47, 50, 0, 50};

        // Initialize SoftAssertions
        SoftAssertions softly = new SoftAssertions();

        // Iterate over each item and assert its properties
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
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

    @Test
    @DisplayName("Backstage pass item - while sellIn < 11 and sellIn > 5 then increment quality by 2 and max value is 50 ")
    void givenBackstagePassItemWithPositiveSellIn_whenUpdateQualityIsCalledAndWhileSellInBetweenFiveAndTen_thenShouldIncrementQualityByTwoAndMaxValueIsFifty(){

        // Arrange

        int days = 10;
        items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 13, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 42),
            new Item("Backstage passes to a TAFKAL80ETC concert", 11, 44),
            new Item("Backstage passes to a TAFKAL80ETC concert", 18, 35)
        };
        app = new GildedRose(items);

        // Act

        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }

        // Assert


        // Expected sellIn values after 10 days
        int[] expectedSellIn = {3, 1, 1, 8};

        // Expected quality values after 10 days
        int[] expectedQuality = {39, 50, 50, 47};

        // Initialize SoftAssertions
        SoftAssertions softly = new SoftAssertions();

        // Iterate over each item and assert its properties
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
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

    @Test
    @DisplayName("Backstage pass item - while sellIn < 5 then increment quality by 3 and max value is 50 ")
    void givenBackstagePassItemWithPositiveSellIn_whenUpdateQualityIsCalledAndWhileSellInInfToFive_thenShouldIncrementQualityByThreeAndMaxValueIsFifty(){

        // Arrange

        int days = 6;
        items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 8, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 8, 42),
            new Item("Backstage passes to a TAFKAL80ETC concert", 6, 38),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 35)
        };
        app = new GildedRose(items);

        // Act

        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }

        // Assert

        // Expected item names after 10 days
        String[] expectedNames = {
            "Backstage passes to a TAFKAL80ETC concert",
            "Backstage passes to a TAFKAL80ETC concert",
            "Backstage passes to a TAFKAL80ETC concert",
            "Backstage passes to a TAFKAL80ETC concert"

        };


        // Expected sellIn values after 10 days
        int[] expectedSellIn = {2, 2, 0, -1};

        // Expected quality values after 10 days
        int[] expectedQuality = {35, 50, 50, 0};

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

    @Test
    @DisplayName("Aged brie item - increase quality by 1 if sellIn > 0 and max value 50")
    void givenAgedBrieItemWithDifferentValueOfSellIn_whenUpdateQualityIsCalled_thenShouldIncreaseQualityByOneAndMaxValueIsFifty(){

        // Arrange

        int days = 5;
        items = new Item[] {
            new Item("Aged Brie", 12, 44),
            new Item("Aged Brie", 11, 43),
            new Item("Aged Brie", 10, 42),
            new Item("Aged Brie", 9, 45),
            new Item("Aged Brie", 8, 38),
            new Item("Aged Brie", 7, 49),
            new Item("Aged Brie", 6, 48),
            new Item("Aged Brie", 5, 50)
        };
        app = new GildedRose(items);

        // Act

        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }

        // Assert

        // Expected sellIn values after 10 days
        int[] expectedSellIn = {7, 6, 5, 4, 3, 2, 1, 0};

        // Expected quality values after 10 days
        int[] expectedQuality = {49, 48, 47, 50, 43, 50, 50, 50};

        // Initialize SoftAssertions
        SoftAssertions softly = new SoftAssertions();

        // Iterate over each item and assert its properties
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
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

    @Test
    @DisplayName("Aged brie item - increase quality by 2 if sellIn < 0 and max value 50")
    void givenAgedBrieItemWithDifferentValueOfSellIn_whenUpdateQualityIsCalledAndSellInInfToZero_thenShouldIncreaseQualityByTwoAndMaxValueIsFifty(){

        // Arrange

        int days = 4;
        items = new Item[] {
            new Item("Aged Brie", 6, 44),
            new Item("Aged Brie", 5, 30),
            new Item("Aged Brie", 4, 32),
            new Item("Aged Brie", 3, 44),
            new Item("Aged Brie", 2, 45),
            new Item("Aged Brie", 1, 46),
            new Item("Aged Brie", 0, 48),
            new Item("Aged Brie", 5, 38)
        };
        app = new GildedRose(items);

        // Act

        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }

        // Assert

        // Expected sellIn values after 10 days
        int[] expectedSellIn = {2, 1, 0, -1, -2, -3, -4, 1};

        // Expected quality values after 10 days
        int[] expectedQuality = {48, 34, 36, 49, 50, 50, 50, 42};

        // Initialize SoftAssertions
        SoftAssertions softly = new SoftAssertions();

        // Iterate over each item and assert its properties
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
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
