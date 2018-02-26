package com.wilsonfranca.currencyconverter.converter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wilson on 24/02/18.
 */
@Controller
@RequestMapping(value = "/converter")
public class ConverterController {

    public String converter() {
        return "converter.html";
    }
}
