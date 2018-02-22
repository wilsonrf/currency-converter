package com.wilsonfranca.currencyconverter.currency;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wilson on 21/02/18.
 */
@Controller
public class CurrencyController {

    @RequestMapping(produces = {"text/html"}, value = "/")
    public String index() {
        return "index.html";
    }

}
