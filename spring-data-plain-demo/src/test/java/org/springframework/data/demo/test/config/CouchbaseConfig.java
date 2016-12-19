package org.springframework.data.demo.test.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.WriteResultChecking;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by corneil on 5/27/14.
 */
@Profile("couchbase")
@Configuration
@EnableCouchbaseRepositories(basePackages = {"org/springframework/data/demo/repository"})
@PropertySource("classpath:META-INF/spring/database.properties")
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {
    private final static Logger logger = LoggerFactory.getLogger(CouchbaseConfig.class);
    @Value("${couchbase.host}")
    protected String host;
    @Value("${couchbase.password}")
    protected String password;
    @Value("${couchbase.port}")
    protected String port;

    @Override
    protected List<String> getBootstrapHosts() {
        logger.info("couchbase.host:" + host);
        StringTokenizer tokens = new StringTokenizer(host, ", ");
        List<String> hosts = new ArrayList<String>();
        while (tokens.hasMoreTokens()) {
            hosts.add(tokens.nextToken());
        }
        return hosts;
    }

    @Override
    public CouchbaseTemplate couchbaseTemplate() throws Exception {
        CouchbaseTemplate template = super.couchbaseTemplate();
        template.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        return template;
    }

    @Override
    protected String getBucketName() {
        return "sd";
    }

    @Override
    protected String getBucketPassword() {
        return password;
    }
}
