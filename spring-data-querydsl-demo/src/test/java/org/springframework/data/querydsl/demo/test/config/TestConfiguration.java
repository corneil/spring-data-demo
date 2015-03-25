package org.springframework.data.querydsl.demo.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.data.querydsl.demo.config.AppConfig;

import javax.annotation.PostConstruct;


@Configuration
@Import({AppConfig.class, JpaHibernateConfig.class, MongoTestConfig.class})
@ImportResource({"classpath:META-INF/spring/repo-populate.xml"})
public class TestConfiguration {
    @Autowired
    protected Environment env;

    @PostConstruct
    public void reportSpringProfile() {
        for (String profile : env.getActiveProfiles()) {
            System.out.println("==== Active Profile:" + profile + " ====");
        }
    }
}
