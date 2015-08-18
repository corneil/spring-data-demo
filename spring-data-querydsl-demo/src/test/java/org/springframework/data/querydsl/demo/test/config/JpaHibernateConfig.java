package org.springframework.data.querydsl.demo.test.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Profile("jpa-hibernate")
@Configuration
@EnableJpaRepositories("org.springframework.data.querydsl.demo.repository")
@PropertySource("classpath:META-INF/spring/database.properties")
@EnableTransactionManagement()
public class JpaHibernateConfig implements TransactionManagementConfigurer {
    @Value("${database.driverClassName}")
    protected String driverClassName;
    @Value("${database.url}")
    protected String url;
    @Value("${database.username}")
    protected String username;
    @Value("${database.password}")
    protected String password;
    @Value("${database.type}")
    protected String databaseType;
    @Value("${database.showSql}")
    protected String showSql;

    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        return jpaTransactionManager;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(3);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(dataSource());
        lcemfb.setJpaDialect(new HibernateJpaDialect());
        lcemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lcemfb.setPackagesToScan("org.springframework.data.demo");
        lcemfb.setPersistenceUnitName("persistenceUnit");
        lcemfb.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        lcemfb.getJpaPropertyMap().put("hibernate.ejb.naming_strategy", org.hibernate.cfg.ImprovedNamingStrategy.class.getCanonicalName());
        if ("mysql".equals(databaseType)) {
            lcemfb.getJpaPropertyMap().put("hibernate.dialect", org.hibernate.dialect.MySQL5InnoDBDialect.class.getCanonicalName());
        } else if ("h2".equals(databaseType)) {
            lcemfb.getJpaPropertyMap().put("hibernate.dialect", org.hibernate.dialect.H2Dialect.class.getCanonicalName());
        } else if ("derby".equals(databaseType)) {
            lcemfb.getJpaPropertyMap().put("hibernate.dialect", org.hibernate.dialect.DerbyTenSevenDialect.class.getCanonicalName());
        } else {
            throw new RuntimeException("database.type must be configured");
        }
        lcemfb.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "update");
        lcemfb.getJpaPropertyMap().put("hibernate.hbm2ddl.format", "false");
        lcemfb.getJpaPropertyMap().put("hibernate.hbm2ddl.export", "true");
        lcemfb.getJpaPropertyMap().put("cache.provider_class", "org.hibernate.cache.NoCacheProvider");
        lcemfb.afterPropertiesSet();
        return lcemfb.getObject();
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(Boolean.valueOf(showSql));
        if ("mysql".equals(databaseType)) {
            jpaVendorAdapter.setDatabase(Database.MYSQL);
            jpaVendorAdapter.setDatabasePlatform(org.hibernate.dialect.MySQL5InnoDBDialect.class.getCanonicalName());
        } else if ("h2".equals(databaseType)) {
            jpaVendorAdapter.setDatabase(Database.H2);
            jpaVendorAdapter.setDatabasePlatform(org.hibernate.dialect.H2Dialect.class.getCanonicalName());
        } else if ("derby".equals(databaseType)) {
            jpaVendorAdapter.setDatabase(Database.DERBY);
            jpaVendorAdapter.setDatabasePlatform(org.hibernate.dialect.DerbyTenSevenDialect.class.getCanonicalName());
        } else {
            throw new RuntimeException("database.type must be configured");
        }
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }
}
