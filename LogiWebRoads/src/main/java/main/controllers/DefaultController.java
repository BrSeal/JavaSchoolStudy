package main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @RequestMapping({"/","/loginPage"})
    public String showLoginPage() {
        return "loginPage";
    }

    @RequestMapping({"/about"})
    public String showAbout() {
        return "about";
    }
}

