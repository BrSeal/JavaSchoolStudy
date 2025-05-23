package main.global.defaultController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping({"/","/home"})
    public String showHomePage() {
        return "home";
    }

    @RequestMapping("/loginPage")
    public String showLoginPage() {
        return "loginPage";
    }

    @RequestMapping("/adminPage")
    public String showAdminPage() {
        return "adminPage";
    }

    @RequestMapping("/about")
    public String showAbout() {
        return "about";
    }

    @RequestMapping("/accessDenied")
    public String showAccessDenied() {
        return "accessDenied";
    }

}

