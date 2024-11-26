package com.gildedrose;

import com.gildedrose.factory.ItemBuilder;
import com.gildedrose.model.Item;
import com.gildedrose.service.GildedRose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GildedRoseApplication implements CommandLineRunner {

    private final GildedRose gildedRose;

    private static final Logger logger = LoggerFactory.getLogger(GildedRose.class);

    // Use constructor injection to let Spring inject the GildedRose service
    @Autowired
    public GildedRoseApplication(GildedRose gildedRose) {
        this.gildedRose = gildedRose;
    }

    @Override
    public void run(String... args) throws Exception {

        // Initialize items
        Item[] items = new Item[]{
            new ItemBuilder().withName("+5 Dexterity Vest").withSellIn(10).withQuality(20).build(),
            new Item("Aged Brie", 2, 0), //
            new Item("Elixir of the Mongoose", 5, 7), //
            new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Conjured Mana Cake", 3, 6)
        };

        // Print the initial items to the console
        logger.info(" Initial items ... ");
        for (Item item : items) {
            logger.info(" {} , {}  , {} ", item.name, item.sellIn, item.quality);
        }

        // Pass items to the GildedRose instance
        gildedRose.initialize(items);

        int days =10;
        for (int i = 0; i < days; i++) {
            gildedRose.updateQuality();
        }

        logger.info(" Updated items after {} days ... ", days);
        // Print the updated items to the console
        for (Item item : gildedRose.getItems()) {
            logger.info(" {} , {}  , {} ", item.name, item.sellIn, item.quality);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(GildedRoseApplication.class, args);
    }
}
