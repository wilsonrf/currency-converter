package com.wilsonfranca.currencyconverter.converter;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.wilsonfranca.currencyconverter.currency.Currency;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by wilson.franca on 27/02/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("integration-test")
public class ConverterServiceTest {


    @ClassRule
    public static WireMockClassRule wireMockClassRule = new WireMockClassRule(9090);

    @Autowired
    ConverterService converterService;

    @Test
    public void test_if_query_latest_rate_successful_from_us_to_ars() {

        ConverterRate converterRate = converterService.lastest(Currency.USD, Currency.ARS, Double.valueOf(1));
        assertThat(converterRate.getRate()).isEqualTo(Double.valueOf(9.750101));
    }

    @Test
    public void test_if_query_historical_rate_successful_from_us_to_ars() throws ParseException {

        Date date = new SimpleDateFormat("yyyy-MM-DD").parse("2018-01-01");

        ConverterRate converterRate = converterService.historical(Currency.USD, Currency.ARS, Double.valueOf(1), date);
        assertThat(converterRate.getRate()).isEqualTo(Double.valueOf(9.750101));
    }
}
