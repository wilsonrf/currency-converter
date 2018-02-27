package com.wilsonfranca.currencyconverter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wilson.franca on 27/02/18.
 */
@Configuration
@ConfigurationProperties(prefix = "oxr")
public class OpenExchangeConfigurationProperties {

    private App app;
    private Urls urls;

    public String getBase() {
        return app.getBase();
    }

    public String getId() {
        return app.getId();
    }

    public String getBaseUrl() {
        return urls.getBase();
    }

    public String getLatestUrl() {
        return urls.getBase() + urls.getLatest();
    }

    public String getHistoricalUrl(Date date) {

        String path = new SimpleDateFormat("yyyy-MM-DD").format(date);

        final String format = String.format(urls.getHistorical(), path);

        return urls.getBase() + format;
    }


    public static class App {
        private String base;
        private String id;

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class Urls {
        private String base;
        private String latest;
        private String historical;

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getLatest() {
            return latest;
        }

        public void setLatest(String latest) {
            this.latest = latest;
        }

        public String getHistorical() {
            return historical;
        }

        public void setHistorical(String historical) {
            this.historical = historical;
        }
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }
}
