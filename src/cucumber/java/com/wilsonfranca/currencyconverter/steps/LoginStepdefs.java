package com.wilsonfranca.currencyconverter.steps;

import com.wilsonfranca.currencyconverter.auth.person.Person;
import com.wilsonfranca.currencyconverter.auth.person.PersonRepository;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;


/**
 * Created by wilson on 01/03/18.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(loader = SpringBootContextLoader.class)
@ActiveProfiles("test")
public class LoginStepdefs implements En {

    WebDriver webDriver;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private ResourceLoader resourceLoader;


    @Before
    public void setUp() throws IOException {

        System.setProperty("webdriver.chrome.driver", "build/chrome-driver/chromedriver");

        webDriver = new ChromeDriver(DesiredCapabilities.chrome());

        Person person = new Person();
        person.setEmail("wilsonrf@gmail.com");
        person.setPassword("123456");
        person.setFirstName("Wilson");
        person.setLastName("Franca");
        person.addAuthorities("CONVERTER");
        personRepository.save(person);

    }


    public LoginStepdefs() {

        When("^the user access the home page$", () -> {
            webDriver.get("http://localhost:8080");
        });

        Then("^the browser redirects the user to the login page$", () -> {

            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            wait.until(ExpectedConditions.urlMatches("login.html"));

        });

        When("^the user enters username (.+)$", (String username) -> {
            WebElement usernameElement = webDriver.findElement(By.id("username"));
            usernameElement.sendKeys(username);
        });


        And("^the user enters the (.+)$", (String password) -> {
            WebElement passwordElement = webDriver.findElement(By.id("password"));
            passwordElement.sendKeys(password);
        });


        And("^push the submit button$", () -> {
            WebElement submit = webDriver.findElement(By.className("btn-primary"));
            submit.submit();
        });

        Then("^the browser redirects the user to the conversion page$", () -> {

            WebDriverWait wait = new WebDriverWait(webDriver, 1);
            wait.until(ExpectedConditions.urlMatches("converter.html"));
            webDriver.close();

        });

        Then("^the browser redirects the user to the login error page$", () -> {
            WebDriverWait wait = new WebDriverWait(webDriver, 1);
            wait.until(ExpectedConditions.urlMatches("login-error.html"));
            webDriver.close();
        });

    }
}
