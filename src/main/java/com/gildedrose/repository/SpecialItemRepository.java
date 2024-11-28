package com.gildedrose.repository;

import com.gildedrose.entity.SpecialItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SpecialItemRepository extends ReactiveCrudRepository<SpecialItem, String> {
}
