package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @RequestMapping({"/","/index"})
    public ModelAndView showIndexPage() {
        return new ModelAndView("index");
    }

    @RequestMapping({"/about"})
    public ModelAndView showAbout() {
        return new ModelAndView("about");
    }
}

