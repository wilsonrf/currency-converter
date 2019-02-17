package com.wilsonfranca.currencyconverter.auth;

public class ReCaptchaInvalidException extends RuntimeException {
    public ReCaptchaInvalidException(String message) {
        super(message);
    }
}
