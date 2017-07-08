package org.springframework.data.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.io.IOException;

@Configuration
@ComponentScan({"org.springframework.data.demo.service", "org.springframework.data.demo.data"})
public class AppConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() throws IOException {
		PropertySourcesPlaceholderConfigurer pc = new PropertySourcesPlaceholderConfigurer();
		pc.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath:META-INF/spring/*.properties"));
		return pc;
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	@Bean(name = "validator")
	public LocalValidatorFactoryBean validatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
		Resource sourceData = new ClassPathResource("data.json");
		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[]{sourceData});
		return factory;
	}
}
