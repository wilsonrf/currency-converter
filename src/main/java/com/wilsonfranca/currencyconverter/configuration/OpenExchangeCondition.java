package com.wilsonfranca.currencyconverter.configuration;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by wilson.franca on 27/02/18.
 */
public class OpenExchangeCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("client") == null ||
                context.getEnvironment().getProperty("client").contains("oxr")
                || context.getEnvironment().getProperty("client").isEmpty();
    }
}
