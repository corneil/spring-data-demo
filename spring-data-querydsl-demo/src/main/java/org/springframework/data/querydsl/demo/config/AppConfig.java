package org.springframework.data.querydsl.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.io.IOException;

@Configuration
@ComponentScan({"org.springframework.data.querydsl.demo.config", "org.springframework.data.querydsl.demo.service"})
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() throws IOException {
        PropertySourcesPlaceholderConfigurer pc = new PropertySourcesPlaceholderConfigurer();
        pc.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath:META-INF/spring/*.properties"));
        return pc;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
