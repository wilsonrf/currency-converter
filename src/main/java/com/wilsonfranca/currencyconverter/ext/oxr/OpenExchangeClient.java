package com.wilsonfranca.currencyconverter.ext.oxr;

import com.wilsonfranca.currencyconverter.configuration.OpenExchangeConfigurationProperties;
import com.wilsonfranca.currencyconverter.converter.ConverterRate;
import com.wilsonfranca.currencyconverter.currency.Currency;
import com.wilsonfranca.currencyconverter.ext.CurrencyConverterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.Date;

/**
 * Created by wilson.franca on 27/02/18.
 */
@Service
public class OpenExchangeClient implements CurrencyConverterClient {

    private RestTemplate restTemplate;

    private OpenExchangeConfigurationProperties configurationProperties;

    @Autowired
    public OpenExchangeClient(@Qualifier("oxrRestTemplate") RestTemplate restTemplate, OpenExchangeConfigurationProperties configurationProperties) {
        this.restTemplate = restTemplate;
        this.configurationProperties = configurationProperties;
    }

    @Override
    public ConverterRate lastest(Currency from, Currency to, Double amount) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(configurationProperties.getLatestUrl())
                .queryParam("app_id", configurationProperties.getId())
                .queryParam("symbols", to.name())
                .queryParam("base", from.name());

        ResponseEntity<OpenExchangeLatestResponse> response = restTemplate.getForEntity(uriComponentsBuilder.build().toUri(),
                OpenExchangeLatestResponse.class);

        OpenExchangeLatestResponse body = response.getBody();

        ConverterRate converterRate = new ConverterRate(from, to, amount, body.getRates().get(to.name()), body.getTimestamp());

        return converterRate;
    }

    @Override
    public ConverterRate historical(Currency from, Currency to, Double amount, Instant when) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(configurationProperties.getHistoricalUrl(Date.from(when)))
                .queryParam("app_id", configurationProperties.getId())
                .queryParam("symbols", to.name())
                .queryParam("base", from.name());

        ResponseEntity<OpenExchangeLatestResponse> response = restTemplate.getForEntity(uriComponentsBuilder.build().toUri(),
                OpenExchangeLatestResponse.class);

        OpenExchangeLatestResponse body = response.getBody();

        ConverterRate converterRate = new ConverterRate(from, to, amount, body.getRates().get(to.name()), body.getTimestamp());

        return converterRate;
    }
}
