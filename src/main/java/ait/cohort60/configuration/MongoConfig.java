package ait.cohort60.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "ait.cohort60.accounting.dao")
public class MongoConfig {
}
