package com.wilsonfranca.currencyconverter.converter;

import com.wilsonfranca.currencyconverter.currency.Currency;
import com.wilsonfranca.currencyconverter.ext.CurrencyConverterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * Created by wilson.franca on 27/02/18.
 */
@Service
public class ConverterService {


    private CurrencyConverterClient currencyConverterClient;

    private ConverterRateService converterRateService;

    @Autowired
    public ConverterService(CurrencyConverterClient currencyConverterClient,
                            ConverterRateService converterRateService) {
        this.currencyConverterClient = currencyConverterClient;
        this.converterRateService = converterRateService;
    }

    public ConverterRate lastest(Currency from, Currency to, Double amount) {
        ConverterRate rate = currencyConverterClient.lastest(from, to, amount);
        if(Objects.nonNull(rate)) {
            ConverterRate saved = converterRateService.save(rate);
            return saved;
        }
        return rate;
    }

    public ConverterRate historical(Currency from, Currency to, Double amount, Date date) {
        return currencyConverterClient.historical(from, to, amount, date.toInstant());
    }

    public Collection<ConverterRate> getLastUserRates(String user) {
        return converterRateService.getLastUserRates(user, 10);
    }

    public ConverterRate save(ConverterRate converterRate) {
        return converterRateService.save(converterRate);
    }

}
