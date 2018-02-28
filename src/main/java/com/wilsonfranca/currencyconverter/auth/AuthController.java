package com.wilsonfranca.currencyconverter.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by wilson on 24/02/18.
 */
@Controller
public class AuthController {

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
    public String signup(Model model) {

        return "security/signup";
    }

    @PostMapping (value = "signup.html")
    public String signup(@Valid SignupFormData signupFormData, Model model) {

        return "security/signup";
    }
}
