package com.wilsonfranca.currencyconverter.configuration;

import com.wilsonfranca.currencyconverter.ext.CurrencyConverterClient;
import com.wilsonfranca.currencyconverter.ext.oxr.OpenExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wilson.franca on 27/02/18.
 */
@Configuration
public class CurrencyConverterClientConfiguration {

    @Autowired
    OpenExchangeClient openExchangeClient;

    @Bean(name = "currencyConverterClient")
    @Conditional(OpenExchangeCondition.class)
    public CurrencyConverterClient currencyConverterClient() {
        return openExchangeClient;
    }

}
