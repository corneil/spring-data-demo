package org.springframework.data.neo4j.demo.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;

/**
 * Created by Corneil on 2016/01/04.
 */
@EnableNeo4jRepositories(basePackages = {"org.springframework.data.neo4j.demo.repository"})
@Configuration
public class GraphDatabaseConfiguration extends Neo4jConfiguration {
    public GraphDatabaseConfiguration() {
    }

    @Override
    public Neo4jServer neo4jServer() {
        return new RemoteServer("http://localhost:7474");
    }

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("org.springframework.data.neo4j.demo.data");
    }
}
