package org.springframework.data.demo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Profile("mongo")
@Configuration
@EnableMongoRepositories("org.springframework.data.demo.repository")
@PropertySource("classpath:META-INF/spring/database.properties")
public class MongoConfig extends AbstractMongoConfiguration {
    protected static Logger logger = LoggerFactory.getLogger(MongoConfig.class);

    protected String databaseName;

    @Value("${mongo.url}")
    protected String url;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }


    @Override
    @Bean
    public Mongo mongo() throws Exception {
        MongoClientURI uri = new MongoClientURI(url);
        logger.info("MongoURL:" + uri);
        databaseName = uri.getDatabase();
        logger.info("Database:" + databaseName);
        return new MongoClient(uri);
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate template = super.mongoTemplate();
        template.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        return template;
    }
}
