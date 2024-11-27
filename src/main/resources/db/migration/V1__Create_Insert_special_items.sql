CREATE TABLE IF NOT EXISTS SPECIAL_ITEM (
    name VARCHAR(255) NOT NULL,
    class_name VARCHAR(255) NOT NULL
);

INSERT INTO SPECIAL_ITEM (name, class_name) VALUES
    ('Aged Brie', 'com.gildedrose.model.items.AgedBrie'),
    ('Backstage passes to a TAFKAL80ETC concert', 'com.gildedrose.model.items.BackstagePass'),
    ('Sulfuras, Hand of Ragnaros', 'com.gildedrose.model.items.Sulfuras'),
    ('Conjured Mana Cake', 'com.gildedrose.model.items.Conjured');
