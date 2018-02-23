package com.wilsonfranca.currencyconverter.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wilson.franca on 23/02/18.
 */
@Configuration
public class JavascriptConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver viewResolver() {
        return new V8ScriptTemplateViewResolver("/public/", ".html");
    }

    @Bean
    public V8ScriptTemplateConfigurer scriptTemplateConfigurer() {
        return new V8ScriptTemplateConfigurer("static/polyfill.js", "public/server.js");
    }

    @Bean
    public MappingResourceBundleMessageSource messageSource() {
        MappingResourceBundleMessageSource source = new MappingResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }
}
