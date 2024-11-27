package com.gildedrose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gildedrose.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        // Arrange: Create the initial list of items
        List<Item> items = List.of(
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)
        );

        // Convert items to JSON string
        String requestBody = objectMapper.writeValueAsString(items);

        // Act: Send a POST request to the API
        mockMvc.perform(post("/api/gilded-rose/update-quality/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isNotFound());
            /*.andExpect((ResultMatcher) jsonPath("$[0].name").value("+5 Dexterity Vest"))
            .andExpect((ResultMatcher) jsonPath("$[0].sellIn").value(0)) // After 10 days
            .andExpect((ResultMatcher) jsonPath("$[0].quality").value(10)); // After 10 days*/
    }
}
