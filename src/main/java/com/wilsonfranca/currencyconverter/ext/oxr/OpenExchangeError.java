package com.wilsonfranca.currencyconverter.ext.oxr;

/**
 * Created by wilson on 27/02/18.
 */
public class OpenExchangeError {

    private Boolean error;

    private Integer status;

    private String message;

    private String description;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
