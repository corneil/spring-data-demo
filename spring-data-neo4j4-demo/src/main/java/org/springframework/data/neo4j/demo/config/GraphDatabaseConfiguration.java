package org.springframework.data.neo4j.demo.config;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Corneil on 2016/01/04.
 */
@EnableNeo4jRepositories(basePackages = {"org.springframework.data.neo4j.demo.repository"})
@EnableTransactionManagement
@Configuration
public class GraphDatabaseConfiguration extends Neo4jConfiguration {
    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory(getConfiguration(), "org.springframework.data.neo4j.demo.data");
    }

    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver");
        return config;
    }
}
