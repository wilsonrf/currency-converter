package com.wilsonfranca.currencyconverter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by wilson on 01/03/18.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/cucumber/resources/features",
        glue = {"com.wilsonfranca.currencyconverter", "cucumber.api.spring"})
public class RunCukesTest {
}
