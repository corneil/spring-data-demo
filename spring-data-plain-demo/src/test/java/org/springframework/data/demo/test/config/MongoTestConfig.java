package org.springframework.data.demo.test.config;

import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
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

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;

@Profile("mongo")
@Configuration
@EnableMongoRepositories("org.springframework.data.demo.repository")
@PropertySource("classpath:META-INF/spring/database.properties")
public class MongoTestConfig extends AbstractMongoConfiguration {
    protected static Logger logger = LoggerFactory.getLogger(MongoTestConfig.class);
    @Value("${mongo.url}")
    protected String url;

    @Override
    protected String getDatabaseName() {
        logger.info("getDatabaseName");
        return "sd";
    }

    @Override
    protected String getMappingBasePackage() {
        return org.springframework.data.demo.data.AuditEntry.class.getPackage().getName();
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        logger.warn("*** Using Fake Mongo ***");
        FakeMongo fakeMongo = new FakeMongo("sd");
        return fakeMongo;
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        logger.info("mongoTemplate");
        MongoTemplate template = super.mongoTemplate();
        template.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        return template;
    }

    @SuppressWarnings("deprecation")
    private static class FakeMongo extends Mongo {
        private Fongo fongo;

        public FakeMongo(final String name) throws UnknownHostException {
            this.fongo = new Fongo(name);
        }

        @Override
        public void dropDatabase(String dbName) {
            fongo.dropDatabase(dbName);
        }

        @Override
        public DB getDB(String dbname) {
            return fongo.getDB(dbname);
        }

        @Override
        public List<String> getDatabaseNames() {
            return fongo.getDatabaseNames();
        }

        public MongoClient getMongo() {
            return fongo.getMongo();
        }

        public ServerAddress getServerAddress() {
            return fongo.getServerAddress();
        }

        @Override
        public Collection<DB> getUsedDatabases() {
            return fongo.getUsedDatabases();
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
