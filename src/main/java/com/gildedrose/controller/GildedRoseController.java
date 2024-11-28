package com.gildedrose.controller;

import com.gildedrose.adapter.JsonItem;
import com.gildedrose.model.Item;
import com.gildedrose.service.GildedRose;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
     * Updates item qualities for the specified number of days and tracks daily changes.
     *
     * @param days the number of days to simulate
     * @param jsonItemsFlux the stream of JSON items to process
     * @return the list of daily changes for each item
     */
    @PostMapping("/update-quality/{days}")
    public Mono<ResponseEntity<Map<String, List<JsonItem>>>> updateQuality(
        @PathVariable int days,
        @RequestBody @Valid Flux<JsonItem> jsonItemsFlux) {
        if(days < 0){
            throw new IllegalArgumentException("days must be a positive integer");
        }
        return jsonItemsFlux
            .map(JsonItem::toLegacyItem) // Convert JsonItem to legacy Item
            .collectList()              // Collect all items into a List
            .flatMap(legacyItems -> {
                // Initialize the GildedRose instance with items
                gildedRose.initialize(legacyItems.toArray(new Item[0]));

                // Track daily changes
                Map<String, List<JsonItem>> dailyChanges = new LinkedHashMap<>();

                // Simulate each day
                for (int i = 0; i < days; i++) {
                    gildedRose.updateQuality();

                    // For each day, add the updated items to the daily changes map
                    List<JsonItem> dailyItems = Stream.of(gildedRose.getItems())
                        .map(JsonItem::fromLegacyItem)
                        .toList();
                    dailyChanges.put("Day" + (i + 1), dailyItems);
                }

                // Return the map of daily changes
                return Mono.just(ResponseEntity.ok(dailyChanges));
            });
    }
}
