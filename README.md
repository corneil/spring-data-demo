spring-data-demo
================

[![Build Status](https://travis-ci.org/corneil/spring-data-demo.png?branch=master)](https://travis-ci.org/corneil/spring-data-demo)

The project includes metadata for SpringSource Tool Suite.

The default JPA configuration uses embedded H2.

The MongoConfig has been separated so the [Fongo](https://github.com/fakemongo) can be used during unit tests.

`mongo.fake=true` in `database.properties` will determine if Fongo is used.

If you want to use an actual mongo server to the following:

  * Create the MongoDB database.
  * Update `src/main/resources/META-INF/spring/database.properties` to match the MongoDB instances you want to connect to.


The project build has been converted to Gradle.

Run tests by invoking `gradlew cleanTest check`

The spring xml configuration files are not used anymore but they serve as an example of an equivalent to the JavaConfig.