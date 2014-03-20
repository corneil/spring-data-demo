spring-data-demo
================

[![Build Status](https://travis-ci.org/corneil/spring-data-demo.png?branch=master)](https://travis-ci.org/corneil/spring-data-demo)

The project includes metadata for SpringSource Tool Suite.

The default JPA configuration uses embedded H2. (I had weird problems with Derby)

Before running the demo:

  * Create the MongoDB database.
  * Update src/main/resources/META-INF/spring/database.properties to match the MongoDB instances you want to connect to.


The project build has been converted to Gradle.

Run demo by invoking gradlew test testMongo

The spring xml configuration files are not used anymore but they serve as an example of an equivalent to the JavaConfig.

