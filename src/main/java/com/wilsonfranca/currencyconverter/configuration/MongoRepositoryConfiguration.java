package com.wilsonfranca.currencyconverter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by wilson.franca on 28/02/18.
 */
@Configuration
@EnableMongoRepositories(basePackages = {"com.wilsonfranca.currencyconverter"})
@EnableMongoAuditing
public class MongoRepositoryConfiguration {


    @Bean
    public AuditorAware<String> auditorAware() {
        return new BasicSecurityAuditorAware();
    }



}
