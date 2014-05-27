package org.springframework.data.demo.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.demo.AppConfig;
import org.springframework.data.demo.CouchbaseConfig;
import org.springframework.data.demo.JpaHibernateConfig;
import org.springframework.data.demo.MongoConfig;

@Configuration
@Import({AppConfig.class, JpaHibernateConfig.class, MongoConfig.class, CouchbaseConfig.class})
public class TestConfiguration {
}
