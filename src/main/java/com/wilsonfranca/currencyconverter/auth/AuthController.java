package com.wilsonfranca.currencyconverter.auth;

import com.wilsonfranca.currencyconverter.auth.person.Person;
import com.wilsonfranca.currencyconverter.auth.person.PersonExistsException;
import com.wilsonfranca.currencyconverter.auth.person.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by wilson on 24/02/18.
 */
@Slf4j
@Controller
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PersonService personService;

    private CaptchaService captchaService;

    @Autowired
    public AuthController(PersonService personService, CaptchaService captchaService) {
        this.personService = personService;
        this.captchaService = captchaService;
    }

    @RequestMapping(value = "login.html")
    public String login() {
        return "security/login";
    }


    @RequestMapping(value = "login-error.html")
    public String loginError(Model model) {
        model.addAttribute("error", true);
        return "security/login";
    }

    @GetMapping(value = "signup.html")
    public String signup(Model model, SignupFormData signupFormData) {
        log.info("Start signup");
        return "security/signup";
    }

    @PostMapping (value = "signup.html")
    public String signup(@Valid SignupFormData signupFormData,
                         @RequestParam(name="g-recaptcha-response") String recaptchaResponse,
                         BindingResult bindingResult, Model model) {

        log.info("Start to create new user");

        if(bindingResult.hasErrors()) {
            log.info("There are  binding errors");
            return "security/signup";
        } else {
            log.info("There are no binding errors");
            captchaService.processResponse(recaptchaResponse);

            try {
                Person person = personService.register(signupFormData);
                model.addAttribute("new_user", true);
                model.addAttribute("user", person.getFirstName() + ' ' +person.getLastName());
                model.addAttribute("username", person.getEmail());

                return "security/login";
            } catch (PersonExistsException e) {
                logger.error("The user is already registred", e);
                model.addAttribute("registered", true);
            }

            return "security/signup";
        }
    }
}
