package org.springframework.data.demo.test.config;

import com.github.fakemongo.Fongo;
import com.mongodb.*;
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
import org.springframework.util.Assert;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;

@Profile("mongo")
@Configuration
@EnableMongoRepositories("org.springframework.data.demo.repository")
@PropertySource("classpath:META-INF/spring/database.properties")
public class MongoTestConfig extends AbstractMongoConfiguration {
    protected static Logger logger = LoggerFactory.getLogger(MongoTestConfig.class);

    protected String databaseName = "sd";

    @Value("${mongo.url}")
    protected String url;

    protected Mongo localMongo;

    @Override
    protected String getDatabaseName() {
        logger.info("getDatabaseName");
        if (localMongo == null) {
            try {
                mongo();
            } catch (Exception e) {
                logger.error("mongo:" + e, e);
            }
        }
        Assert.notNull(databaseName);
        Assert.hasText(databaseName);
        return databaseName;
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        logger.info("mongoTemplate");
        if (localMongo == null) {
            mongo();
        }
        MongoTemplate template = super.mongoTemplate();
        template.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        return template;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        logger.warn("*** Using Fake Mongo ***");
        FakeMongo fakeMongo = new FakeMongo("sd");
        DB sdDb = fakeMongo.getDB("sd");
        databaseName = sdDb.getName();
        logger.info("databaseName=" + databaseName);
        Assert.notNull(databaseName);
        Assert.hasText(databaseName);
        localMongo = fakeMongo;
        return localMongo;
    }

    private static class FakeMongo extends Mongo {
        private Fongo fongo;

        public FakeMongo(final String name) throws UnknownHostException {
            this.fongo = new Fongo(name);
        }

        @Override
        public DB getDB(String dbname) {
            return fongo.getDB(dbname);
        }

        @Override
        public Collection<DB> getUsedDatabases() {
            return fongo.getUsedDatabases();
        }

        @Override
        public List<String> getDatabaseNames() {
            return fongo.getDatabaseNames();
        }

        @Override
        public void dropDatabase(String dbName) {
            fongo.dropDatabase(dbName);
        }

        public ServerAddress getServerAddress() {
            return fongo.getServerAddress();
        }

        public MongoClient getMongo() {
            return fongo.getMongo();
        }

        @Override
        public WriteConcern getWriteConcern() {
            return fongo.getWriteConcern();
        }

        @Override
        public String toString() {
            return fongo.toString();
        }
    }
}
