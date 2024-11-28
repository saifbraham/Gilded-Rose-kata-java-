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
    public void testUpdateQualityValidRequest() {
        // Arrange: Create a valid list of JsonItems
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
            .jsonPath("$.Day1[0].name").isEqualTo("+5 Dexterity Vest") // Check Day 1
            .jsonPath("$.Day1[0].sellIn").isEqualTo(9)                 // After Day 1
            .jsonPath("$.Day1[0].quality").isEqualTo(19)              // After Day 1
            .jsonPath("$.Day10[1].name").isEqualTo("Aged Brie")        // Check Day 10
            .jsonPath("$.Day10[1].sellIn").isEqualTo(-8)              // After 10 days
            .jsonPath("$.Day10[1].quality").isEqualTo(18);            // After 10 days
    }

    @Test
    public void testUpdateQualityInvalidDays() {
        // Arrange: Create a valid list of JsonItems
        List<JsonItem> jsonItems = List.of(
            new JsonItem("+5 Dexterity Vest", 10, 20)
        );

        // Act & Assert: Send a POST request with invalid days
        webTestClient.post()
            .uri("/api/gilded-rose/update-quality/-5")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(jsonItems)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody()
            .jsonPath("$.error").isEqualTo("days must be a positive integer");
    }

    @Test
    public void testUpdateQualityMissingField() {
        // Arrange: Create an invalid JsonItem with a missing "name" field
        String invalidPayload = """
            [
                {"sellIn": 10, "quality": 20}
            ]
            """;

        // Act & Assert: Send a POST request with invalid payload
        webTestClient.post()
            .uri("/api/gilded-rose/update-quality/10")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(invalidPayload)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody()
            .jsonPath("$.name").isEqualTo("Item name must not be blank");
    }

    @Test
    public void testUpdateQualityInvalidFieldValues() {
        // Arrange: Create an invalid JsonItem with invalid "sellIn" and "quality" values
        String invalidPayload = """
        [
            {"name": "+5 Dexterity Vest", "sellIn": -1, "quality": -1}
        ]
        """;

        // Act & Assert: Send a POST request with invalid field values
        webTestClient.post()
            .uri("/api/gilded-rose/update-quality/10")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(invalidPayload)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody()
            .jsonPath("$.quality").isEqualTo("Quality must be at least 0");
    }

    @Test
    public void testUpdateQualityEmptyPayload() {
        // Act & Assert: Send a POST request with an empty payload
        webTestClient.post()
            .uri("/api/gilded-rose/update-quality/10")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("[]")
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody()
            .jsonPath("$.error").isEqualTo("Invalid input provided. Please check the request body or parameters.");
    }

    @Test
    public void testUpdateQualityMalformedJson() {
        // Arrange: Create a malformed JSON payload
        String malformedPayload = """
            [
                {"name": "+5 Dexterity Vest", "sellIn": 10, "quality": 20,
            ]
            """;

        // Act & Assert: Send a POST request with malformed JSON
        webTestClient.post()
            .uri("/api/gilded-rose/update-quality/10")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(malformedPayload)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody()
            .jsonPath("$.error").isEqualTo("Invalid input provided. Please check the request body or parameters.");
    }
}
