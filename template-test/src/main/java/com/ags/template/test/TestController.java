package com.ags.template.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Angel
 * @since 19/06/2014
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public ModelAndView hello() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", "AGS");
        return modelAndView;
    }
}
