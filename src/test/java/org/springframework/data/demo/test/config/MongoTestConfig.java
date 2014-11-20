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

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.List;

@Profile("mongo")
@Configuration
@EnableMongoRepositories("org.springframework.data.demo.repository")
@PropertySource("classpath:META-INF/spring/database.properties")
public class MongoTestConfig extends AbstractMongoConfiguration {
    protected static Logger logger = LoggerFactory.getLogger(MongoTestConfig.class);

    protected String databaseName;

    @Value("${mongo.url}")
    protected String url;

    @Value("${mongo.fake}")
    protected String mongoFake;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate template = super.mongoTemplate();
        template.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        return template;
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

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        if ("true".equalsIgnoreCase(mongoFake)) {
            logger.warn("*** Using Fake Mongo ***");
            FakeMongo fakeMongo = new FakeMongo("sd");
            DB sdDb = fakeMongo.getDB("sd");
            databaseName = sdDb.getName();
            return fakeMongo;
        } else {
            MongoClientURI uri = new MongoClientURI(url);
            logger.info("MongoURL:" + uri);
            databaseName = uri.getDatabase();
            logger.info("Database:" + databaseName);
            return new MongoClient(uri);
        }
    }
}
