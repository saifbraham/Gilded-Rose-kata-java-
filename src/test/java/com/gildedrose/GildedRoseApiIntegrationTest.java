package com.gildedrose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gildedrose.adapter.JsonItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GildedRoseApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testUpdateQuality() throws Exception {
        // Arrange: Create the initial list of JsonItems
        List<JsonItem> jsonItems = List.of(
            new JsonItem("+5 Dexterity Vest", 10, 20),
            new JsonItem("Aged Brie", 2, 0),
            new JsonItem("Sulfuras, Hand of Ragnaros", 0, 80),
            new JsonItem("Backstage passes to a TAFKAL80ETC concert", 15, 20)
        );

        // Convert JsonItems to JSON string
        String requestBody = objectMapper.writeValueAsString(jsonItems);

        // Act: Send a POST request to the API
        mockMvc.perform(post("/api/gilded-rose/update-quality/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("+5 Dexterity Vest"))
            .andExpect(jsonPath("$[0].sellIn").value(0)) // After 10 days
            .andExpect(jsonPath("$[0].quality").value(10)) // After 10 days
            .andExpect(jsonPath("$[1].name").value("Aged Brie"))
            .andExpect(jsonPath("$[1].sellIn").value(-8)) // After 10 days
            .andExpect(jsonPath("$[1].quality").value(18)); // After 10 days
    }
}
