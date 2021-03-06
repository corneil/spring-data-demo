package org.springframework.data.demo.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.demo.config.AppConfig;
import org.springframework.data.demo.config.CouchbaseConfig;

import javax.annotation.PostConstruct;

@Configuration
@Import({AppConfig.class, CouchbaseConfig.class})
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
