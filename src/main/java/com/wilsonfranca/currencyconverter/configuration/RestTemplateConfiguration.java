package com.wilsonfranca.currencyconverter.configuration;

import com.wilsonfranca.currencyconverter.ext.oxr.OpenExchangeErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wilson.franca on 27/02/18.
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean(name = "oxrRestTemplate")
    RestTemplate oxrRestTemplate() {
        OpenExchangeErrorHandler errorHandler = new OpenExchangeErrorHandler();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(errorHandler);
        return restTemplate;
    }
}
