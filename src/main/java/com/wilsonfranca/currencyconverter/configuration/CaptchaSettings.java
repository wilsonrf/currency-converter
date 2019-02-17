package com.wilsonfranca.currencyconverter.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "recaptcha")
public class CaptchaSettings {

    private String url;
    private String siteKey;
    private String secretKey;
}