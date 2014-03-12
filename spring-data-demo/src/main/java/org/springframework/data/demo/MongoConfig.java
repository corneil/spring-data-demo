package org.springframework.data.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

@Profile("mongo")
@Configuration
@EnableMongoRepositories("org.springframework.data.demo.repository")
@PropertySource("classpath:META-INF/spring/database.properties")
public class MongoConfig extends AbstractMongoConfiguration {
	@Value("${mongo.database}")
	protected String databaseName;

	@Value("${mongo.host}")
	protected String host;

	@Value("${mongo.port}")
	protected String port;

	@Override
	protected String getDatabaseName() {
		return databaseName;
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		// TODO Auto-generated method stub
		return new Mongo(host, Integer.parseInt(port));
	}

	@Override
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate template = super.mongoTemplate();
		template.setWriteResultChecking(WriteResultChecking.EXCEPTION);
		return template;
	}
}
