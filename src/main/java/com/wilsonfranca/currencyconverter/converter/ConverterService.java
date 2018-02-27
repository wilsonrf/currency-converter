package com.wilsonfranca.currencyconverter.converter;

import com.wilsonfranca.currencyconverter.currency.Currency;
import com.wilsonfranca.currencyconverter.ext.CurrencyConverterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by wilson.franca on 27/02/18.
 */
@Service
public class ConverterService {


    private CurrencyConverterClient currencyConverterClient;

    @Autowired
    public ConverterService(CurrencyConverterClient currencyConverterClient) {
        this.currencyConverterClient = currencyConverterClient;
    }

    public ConverterRate lastest(Currency from, Currency to, Double amount) {
        return currencyConverterClient.lastest(from, to, amount);
    }

    public ConverterRate historical(Currency from, Currency to, Double amount, Date date) {
        return currencyConverterClient.historical(from, to, amount, date.toInstant());
    }

}
