package com.gildedrose.controller;

import com.gildedrose.adapter.JsonItem;
import com.gildedrose.model.Item;
import com.gildedrose.service.GildedRose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/gilded-rose")
public class GildedRoseController {

    private final GildedRose gildedRose;

    @Autowired
    public GildedRoseController(GildedRose gildedRose) {
        this.gildedRose = gildedRose;
    }

    /**
     * Updates item qualities for the specified number of days.
     *
     * @param days the number of days to simulate
     * @param jsonItems the list of json items to process
     * @return the updated list of items
     */
    @PostMapping("/update-quality/{days}")
    public ResponseEntity<List<JsonItem>> updateQuality(
        @PathVariable int days,
        @RequestBody List<JsonItem> jsonItems) {

        // Convert JsonItem to legacy Item
        List<Item> legacyItems = jsonItems.stream()
            .map(JsonItem::toLegacyItem)
            .toList();

        // Initialize the GildedRose instance with items
        gildedRose.initialize(legacyItems.toArray(new Item[0]));

        // Update quality for the given number of days
        for (int i = 0; i < days; i++) {
            gildedRose.updateQuality();
        }

        List<JsonItem> updatedJsonItems = Stream.of(gildedRose.getItems())
            .map(JsonItem::fromLegacyItem)
            .toList();

        // Return the updated list of items
        return ResponseEntity.ok(updatedJsonItems);
    }
}
