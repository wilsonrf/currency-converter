package com.wilsonfranca.currencyconverter.currency;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wilson on 26/02/18.
 */
public enum Currency {

    USD("United States Dollars"),
    EUR("Euros"),
    GBP("Pound Sterling"),
    JPY("Japanese Yen"),
    BRL("Brazilian Real"),
    ARS("Argentine Pesos"),
    BTC("Bitcon");

    private String currencyName;

    Currency(String currencyName) {
        this.currencyName = currencyName;
    }

    public static List<Currency> getAll() {
        return Arrays.asList(values());
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public static Currency from(String symbol) {
        for (Currency currency: values()) {
            if(currency.name().equalsIgnoreCase(symbol)) {
                return currency;
            }
        }

        throw new IllegalArgumentException("Currency not found");
    }
}
