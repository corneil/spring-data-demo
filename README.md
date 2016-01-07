spring-data-demo
================

[![Build Status](https://travis-ci.org/corneil/spring-data-demo.png?branch=master)](https://travis-ci.org/corneil/spring-data-demo)

The project provides a demonstration for using the following:

  * [Spring Data JPA](http://projects.spring.io/spring-data-jpa)
  * [Spring Data MongoDB](http://projects.spring.io/spring-data-mongodb)
  * [Spring Data Neo4J](http://projects.spring.io/spring-data-neo4j)
  * [QueryDSL](http://www.querydsl.com)
  * [Fongo](https://github.com/fakemongo)
  

The default JPA configuration uses embedded H2.

The Mongo config uses [Fongo](https://github.com/fakemongo) for embedded MongoDB functionality.


The project build has been converted to Gradle.

Run tests by invoking `gradlew cleanTest check`

The spring xml configuration files are not used anymore but they serve as an example of an equivalent to the JavaConfig.