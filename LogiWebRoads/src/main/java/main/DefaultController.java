package main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String showIndexPage() {
        return "index";
    }

    @RequestMapping("/signUp")
    public String showSignIn(){
        return "signUp";
    }

    @RequestMapping("/logIn")
    public String showLogInPage(){
        return "logIn";
    }

    @RequestMapping("/about")
    public String showAboutInPage(){
        return "about";
    }

    @RequestMapping("/priceList")
    public String showPriceListPage(){
        return "priceList";
    }
}

