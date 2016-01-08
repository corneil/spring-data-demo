package org.springframework.data.neo4j.demo.test.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.demo.config.AppConfig;
import org.springframework.data.neo4j.demo.config.GraphDatabaseConfiguration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Import({AppConfig.class, GraphDatabaseConfiguration.class, TestConfiguration.Neo4jDatabase.class})
public class TestConfiguration {
    @Configuration
    static class Neo4jDatabase {
        @Bean
        GraphDatabaseService graphDatabaseService() throws IOException {
            return new TestGraphDatabaseFactory().newImpermanentDatabase();
            /*
            File store = new File(System.getProperty("java.io.tmpdir"), "neo4j.db");
            System.out.println("Store:" + store.getAbsolutePath());
            return new TestGraphDatabaseFactory().newEmbeddedDatabase(store);
            */
        }
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
