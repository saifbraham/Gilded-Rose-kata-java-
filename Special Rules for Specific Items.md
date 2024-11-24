# General Rules for Quality:
### Maximum Quality:

 - The Quality of an item is never more than 50 (except for "Sulfuras," which has a fixed Quality of 80).

### Minimum Quality:

 - The Quality of an item is never less than 0.

### Daily Degradation:

 - The Quality of an item decreases by 1 every day.

### Expired Items:

 - Once the SellIn value is 0 or less, the Quality of the item decreases twice as fast (i.e., by 2 daily).



# Special Rules for Specific Items:
### 1. Backstage Passes to a TAFKAL80ETC Concert:
Quality increases as the `SellIn` value approaches:
 - Increases by 1 when there are more than 10 days remaining.
 - Increases by 2 when there are 6–10 days remaining.
 - Increases by 3 when there are 1–5 days remaining.

Once the concert date passes (SellIn <= 0), the Quality drops to 0.
### 2. Aged Brie
**Increases in Quality** as it ages (by **1** daily).\
After the `SellIn` date, the Quality increases by **2** daily.\
The Quality still cannot exceed 50.

### 3. Sulfuras, Hand of Ragnaros:
 - Its Quality is fixed at 80 and never changes.
 - Its SellIn value also does not change.

### 4. Standard items (DexterityVest and ElixirMongoose)
Quality decreases as the `SellIn` value approaches:
- Decreases by 1 when `SellIn` value positive.
- Decreases by 2 when `SellIn` value negative.

Quality evaluated by a positive value
