package com.wilsonfranca.currencyconverter.auth;

public class InvalidReCaptchaException extends RuntimeException {
    public InvalidReCaptchaException(String message) {
        super(message);
    }
}
