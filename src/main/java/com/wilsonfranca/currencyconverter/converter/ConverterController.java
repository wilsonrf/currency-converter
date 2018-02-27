package com.wilsonfranca.currencyconverter.converter;

import com.wilsonfranca.currencyconverter.currency.Currency;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by wilson on 24/02/18.
 */
@Controller
public class ConverterController {


    @RequestMapping(value = "/converter.html")
    public String converter(Model model) {

        List<Currency> currencies = Currency.getAll();
        model.addAttribute("currencies", currencies);

        return "converter/converter";
    }
}
