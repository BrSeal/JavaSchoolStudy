package main.defaultController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping({"/", "/loginPage"})
    public String showLoginPage() {
        return "loginPage";
    }

    @RequestMapping({"/about"})
    public String showAbout() {
        return "about";
    }
}

