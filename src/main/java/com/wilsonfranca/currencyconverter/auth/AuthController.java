package com.wilsonfranca.currencyconverter.auth;

import com.wilsonfranca.currencyconverter.auth.person.Person;
import com.wilsonfranca.currencyconverter.auth.person.PersonExistsException;
import com.wilsonfranca.currencyconverter.auth.person.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by wilson on 24/02/18.
 */
@Controller
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PersonService personService;

    @Autowired
    public AuthController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "login.html")
    public String login() {
        return "security/login";
    }


    @RequestMapping(value = "login-error.html")
    public String logout(Model model) {
        model.addAttribute("error", true);
        return "security/login";
    }

    @GetMapping(value = "signup.html")
    public String signup(Model model, SignupFormData signupFormData) {
        return "security/signup";
    }

    @PostMapping (value = "signup.html")
    public String signup(@Valid SignupFormData signupFormData, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "security/signup";
        } else {

            try {
                Person person = personService.register(signupFormData);
                return "redirect:/login.html";
            } catch (PersonExistsException e) {
                logger.error("The user is already registred", e);
                model.addAttribute("registered", true);
            }

            return "security/signup";
        }
    }
}
