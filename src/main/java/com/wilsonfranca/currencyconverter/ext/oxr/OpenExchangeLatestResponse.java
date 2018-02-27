package com.wilsonfranca.currencyconverter.ext.oxr;

import java.util.Map;

/**
 * Created by wilson.franca on 27/02/18.
 *
 * {
 "disclaimer": "https://openexchangerates.org/terms/",
 "license": "https://openexchangerates.org/license/",
 "timestamp": 1449877801,
 "base": "USD",
 "rates": {
 "ARS": 9.750101
 }
 }
 */
public class OpenExchangeLatestResponse {


    private String disclaimer;
    private String license;
    private Long timestamp;
    private Map<String, Double> rates;

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
