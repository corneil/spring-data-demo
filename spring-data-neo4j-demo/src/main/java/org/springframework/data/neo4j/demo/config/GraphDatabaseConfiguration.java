package org.springframework.data.neo4j.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

/**
 * Created by Corneil on 2016/01/04.
 */
@EnableNeo4jRepositories(basePackages = {"org.springframework.data.neo4j.demo.repository"})
@Configuration
public class GraphDatabaseConfiguration extends Neo4jConfiguration {
    public GraphDatabaseConfiguration() {
        setBasePackage("org.springframework.data.neo4j.demo.data");
    }
}
