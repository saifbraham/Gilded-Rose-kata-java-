# How to handle the initialization special items from either configuration or the database dynamically
 - From application.yml file : set initialize-source to config
```
gildedrose:
    initialize-source: config
```
- From database H2 file : set initialize-source to db
```
gildedrose:
    initialize-source: db
```
By default, initialization special items is from database
