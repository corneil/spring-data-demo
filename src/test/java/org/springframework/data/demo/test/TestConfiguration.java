package org.springframework.data.demo.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.demo.AppConfig;
import org.springframework.data.demo.CouchbaseConfig;
import org.springframework.data.demo.JpaHibernateConfig;


@Configuration
@Import({AppConfig.class, JpaHibernateConfig.class, MongoTestConfig.class, CouchbaseConfig.class})
@ImportResource({"classpath:META-INF/spring/repo-populate.xml"})
public class TestConfiguration {

}
