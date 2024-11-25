# Gilded Rose starting position in Java

## Run the TextTest Fixture from Command-Line

```
./gradlew -q text
```

### Specify Number of Days

For e.g. 10 days:

```
./gradlew -q text --args 10
```

You should make sure the gradle commands shown above work when you execute them in a terminal before trying to use TextTest (see below).


## Run the TextTest approval test that comes with this project

There are instructions in the [TextTest Readme](../texttests/README.md) for setting up TextTest. What's unusual for the Java version is there are two executables listed in [config.gr](../texttests/config.gr) for Java. The first uses Gradle wrapped in a python script. Uncomment these lines to use it:

    executable:${TEXTTEST_HOME}/Java/texttest_rig.py
    interpreter:python

The other relies on your CLASSPATH being set correctly in [environment.gr](../texttests/environment.gr). Uncomment these lines to use it instead:

    executable:com.gildedrose.TexttestFixture
    interpreter:java

## To set up the Java version as a Maven project in your Integrated Development Environment (IDE), follow these steps:

### 1. Clone the Repository:

Open your terminal or command prompt.

Execute the following command to clone the repository:

```
git clone https://github.com/emilybache/GildedRose-Refactoring-Kata.git
```

This will create a directory named GildedRose-Refactoring-Kata containing the project files.

### 2. Navigate to the Java Directory:

Within the cloned repository, navigate to the Java directory:

```
cd GildedRose-Refactoring-Kata/Java
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
