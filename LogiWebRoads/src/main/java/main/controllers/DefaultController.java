package main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @RequestMapping({"/","/loginPage"})
    public ModelAndView showIndexPage() {
        return new ModelAndView("loginPage");
    }

    @RequestMapping({"/about"})
    public ModelAndView showAbout() {
        return new ModelAndView("about");
    }
}

