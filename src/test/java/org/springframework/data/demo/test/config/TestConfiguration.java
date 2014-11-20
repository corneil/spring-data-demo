package org.springframework.data.demo.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.demo.config.AppConfig;
import org.springframework.data.demo.config.CouchbaseConfig;
import org.springframework.data.demo.config.JpaHibernateConfig;


@Configuration
@Import({AppConfig.class, JpaHibernateConfig.class, MongoTestConfig.class, CouchbaseConfig.class})
@ImportResource({"classpath:META-INF/spring/repo-populate.xml"})
public class TestConfiguration {

}
