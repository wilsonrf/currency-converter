package com.wilsonfranca.currencyconverter.index;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wilson.franca on 23/02/18.
 */
@Controller
public class IndexController {

    @GetMapping("/{path:(?!.*.js|.*.css|.*.jpg).*$}")
    public String index(Model model, HttpServletRequest request) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> req = new HashMap<>();

        String root = request.getServletPath().equals("/index.html") ? "/" : request.getServletPath();

        if(request.getQueryString() != null)
            req.put("location", String.format("%s?%s", root, request.getQueryString()));
        else
            req.put("location", root);

        Map<String, Object> state = new HashMap<>();

        state.put("items", Arrays.asList(1,2,3,4,5,6));

        model.addAttribute("req", mapper.writeValueAsString(req));
        model.addAttribute("state", mapper.writeValueAsString(state));

        return "index";
    }
}
