package com.wilsonfranca.currencyconverter.converter;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * Created by wilson on 27/02/18.
 */
public class ConverterFormData {

    @NotNull
    private String from;

    @NotNull
    private String to;

    @NotNull
    @Min(1)
    private Double amount;

    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Instant instant;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }
}
