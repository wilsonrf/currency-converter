package com.wilsonfranca.currencyconverter.converter;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.wilsonfranca.currencyconverter.currency.Currency;
import com.wilsonfranca.currencyconverter.ext.oxr.exception.InvalidDateException;
import com.wilsonfranca.currencyconverter.ext.oxr.exception.NotAvailableException;
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

    @Test(expected = NotAvailableException.class)
    public void test_if_query_historical_rate_of_btc_on_not_available_period_from_usd() throws ParseException {

        Date date = new SimpleDateFormat("yyyy-MM-DD").parse("1945-01-01");

        ConverterRate converterRate = converterService.historical(Currency.USD, Currency.BTC, Double.valueOf(1), date);

    }

    @Test(expected = InvalidDateException.class)
    public void test_if_query_historical_rate_from_usd_to_ars_but_with_invalid_date_in_the_future() throws ParseException {

        Date date = new SimpleDateFormat("yyyy-MM-DD").parse("2055-01-01");

        ConverterRate converterRate = converterService.historical(Currency.USD, Currency.ARS, Double.valueOf(1), date);


    }
}
