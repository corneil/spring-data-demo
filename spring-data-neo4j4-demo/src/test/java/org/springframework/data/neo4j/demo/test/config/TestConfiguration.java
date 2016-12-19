package org.springframework.data.neo4j.demo.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.demo.config.AppConfig;
import org.springframework.data.neo4j.demo.config.GraphDatabaseConfiguration;
import org.springframework.data.neo4j.server.InProcessServer;
import org.springframework.data.neo4j.server.Neo4jServer;

import javax.annotation.PostConstruct;

@Configuration
@Import({AppConfig.class, TestConfiguration.TestNeo4jDatabase.class})
public class TestConfiguration {
    @Configuration
    static class TestNeo4jDatabase extends GraphDatabaseConfiguration {

    }

    @Autowired
    protected Environment env;

    @PostConstruct
    public void reportSpringProfile() {
        for (String profile : env.getActiveProfiles()) {
            System.out.println("==== Active Profile:" + profile + " ====");
        }
    }
}
