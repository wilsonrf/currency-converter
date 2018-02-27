package com.wilsonfranca.currencyconverter.ext;

import com.wilsonfranca.currencyconverter.converter.ConverterRate;
import com.wilsonfranca.currencyconverter.currency.Currency;

import java.time.Instant;

/**
 * Created by wilson.franca on 27/02/18.
 */
public interface CurrencyConverterClient {

    ConverterRate lastest(Currency from, Currency to, Double amount);

    ConverterRate historical(Currency from, Currency to, Double amount, Instant when);

}
