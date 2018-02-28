package com.wilsonfranca.currencyconverter.converter;

import com.wilsonfranca.currencyconverter.currency.Currency;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * Created by wilson.franca on 27/02/18.
 */
@Document
public class ConverterRate {

    private Currency from;

    private Currency to;

    @CreatedDate
    private Instant dateCreated;

    private Instant rateDate;

    private Double amount;

    private Double rate;

    @CreatedBy
    private String user;

    public ConverterRate(){}

    public ConverterRate(Currency from, Currency to, Double amount, Double rate, Long timestamp) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.rate = rate;
        this.rateDate = Instant.ofEpochMilli(timestamp);
    }

    public Currency getFrom() {
        return from;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public Currency getTo() {
        return to;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getRateDate() {
        return rateDate;
    }

    public void setRateDate(Instant rateDate) {
        this.rateDate = rateDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
