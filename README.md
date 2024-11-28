# To set up the Java version as a Maven project in your Integrated Development Environment (IDE),  follow these steps:

### 1. Clone the Repository:

Open your terminal or command prompt.

Execute the following command to clone the repository:

```
git clone https://github.com/saifbraham/Gilded-Rose-kata-java-.git
```

This will create a directory named GildedRose-Refactoring-Kata containing the project files.

### 2. Navigate to the Java Directory:

Within the cloned repository, navigate to the Java directory:

```
cd Gilded-Rose-kata-java-/Java
```

This directory contains the Java implementation of the kata.

### 3.Open Your IDE:

Launch your preferred IDE (e.g., IntelliJ IDEA, Eclipse).

### 4. Import the Project:

In your IDE, choose the option to import a project.

Navigate to the Java directory within the cloned repository and select it.

Choose to import the project as a Maven project. Your IDE should detect the [pom.xml](./pom.xml) file and configure the project accordingly.

### 5. Verify Project Structure:

Ensure that the project structure is correctly set up:

The `src/main/java` directory should be marked as the Sources Root.\
The `src/test/java` directory should be marked as the Test Sources Root.

If these directories are not marked correctly, adjust them in your IDE's project structure settings.

### 6. Build the Project:

Use your IDE's build functionality to compile the project. This will download necessary dependencies and compile the source code.

### 7. Run Tests:

Execute the test cases provided in the project to ensure everything is set up correctly. You can run tests using your IDE's test runner or by executing the following Maven command in the terminal:

```
mvn test
```

## Run the TextTest Fixture from IDE

### Create a Run Configuration:

Right-click on the main class file in the src/test/java directory.

Select `Run 'YourTestMainClass.main()'`. This action will create a temporary run configuration.

### Edit the Run Configuration to Add Arguments:

Go to `Run > Edit Configurations...` in the menu.

In the Run/Debug Configurations dialog, locate the configuration for `YourTestMainClass`.

In the `Program arguments` field, enter the arguments you wish to pass to the `main` method. For example: `10`.

# Rest Api
### .http file with intellij
 - Start spring boot application
 - Open test-post-gildedrose.http file
 - Execute Http Post request

## POST http://localhost:8080/api/gilded-rose/update-quality/10



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

### 5. Conjured Items:
 - Degrade in Quality twice as fast as regular items (by 2 daily).
 - If SellIn is 0 or less, the Quality decreases by 4 daily.
