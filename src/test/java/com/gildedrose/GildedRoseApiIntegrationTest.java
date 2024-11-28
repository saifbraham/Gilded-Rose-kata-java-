package com.gildedrose;

import com.gildedrose.adapter.JsonItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GildedRoseApiIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testUpdateQuality() {
        // Arrange: Create the initial list of JsonItems
        List<JsonItem> jsonItems = List.of(
            new JsonItem("+5 Dexterity Vest", 10, 20),
            new JsonItem("Aged Brie", 2, 0),
            new JsonItem("Sulfuras, Hand of Ragnaros", 0, 80),
            new JsonItem("Backstage passes to a TAFKAL80ETC concert", 15, 20)
        );

        // Act & Assert: Send a POST request and validate the response
        webTestClient.post()
            .uri("/api/gilded-rose/update-quality/10")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(jsonItems)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.Day1[0].name").isEqualTo("+5 Dexterity Vest") // Check Day 1, first item
            .jsonPath("$.Day1[0].sellIn").isEqualTo(9)                 // After Day 1
            .jsonPath("$.Day1[0].quality").isEqualTo(19)              // After Day 1
            .jsonPath("$.Day10[1].name").isEqualTo("Aged Brie")        // Check Day 10, second item
            .jsonPath("$.Day10[1].sellIn").isEqualTo(-8)              // After 10 days
            .jsonPath("$.Day10[1].quality").isEqualTo(18);            // After 10 days
    }
}
