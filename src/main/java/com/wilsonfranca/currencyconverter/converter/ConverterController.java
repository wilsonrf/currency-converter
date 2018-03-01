package com.wilsonfranca.currencyconverter.converter;

import com.wilsonfranca.currencyconverter.currency.Currency;
import com.wilsonfranca.currencyconverter.ext.oxr.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by wilson on 24/02/18.
 */
@Controller
public class ConverterController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private ConverterService converterService;

    @Autowired
    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping(value = "/converter.html")
    public String converter(ConverterFormData converterFormData, Model model, Principal principal) {

        List<Currency> currencies = Currency.getAll();
        model.addAttribute("currencies", currencies);

        Collection<ConverterRate> historic = converterService.getLastUserRates(principal.getName());
        model.addAttribute("historic", historic);

        logger.info("Historic size [{}]", historic.size());

        return "converter/converter";
    }


    @PostMapping(value = "/converter.html")
    public String converter(@Valid ConverterFormData converterFormData,
                            BindingResult bindingResult, Model model, Principal principal) {

        List<Currency> currencies = Currency.getAll();
        model.addAttribute("currencies", currencies);

        Collection<ConverterRate> historic = converterService.getLastUserRates(principal.getName());
        model.addAttribute("historic", historic);

        logger.info("Historic size [{}]", historic.size());

        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(objectError -> System.out.println(objectError));
            return "converter/converter";
        }

        try {

            ConverterRate rate = null;

            if(Objects.nonNull(converterFormData.getInstant())) {
                rate = converterService.historical(Currency.from(converterFormData.getFrom()),
                        Currency.from(converterFormData.getTo()), converterFormData.getAmount(),
                        converterFormData.getInstant());
            } else {
                rate = converterService.lastest(Currency.from(converterFormData.getFrom()),
                        Currency.from(converterFormData.getTo()), converterFormData.getAmount());
            }

            model.addAttribute("rate", rate);

            return "converter/converter-result";

        } catch (ClientException e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "There is a problem with your request.");
        } catch (InvalidDateException e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "The date you trying to query is not valid.");
        } catch (NotAvailableException e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "The date you trying to query is not available.");
        } catch (UnauthorizedException e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "You are not authorized to query now.");
        }  catch (NotFoundException e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "The query you tried is not available.");
        }



        return "converter/converter";
    }
}
