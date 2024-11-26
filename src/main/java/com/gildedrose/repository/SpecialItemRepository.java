package com.gildedrose.repository;

import com.gildedrose.entity.SpecialItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialItemRepository extends JpaRepository<SpecialItem, String> {
}
