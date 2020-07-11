package main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @RequestMapping("/welcome")
    public ModelAndView helloWorld() {
        String message = "<h3>Hello World!!</h3>";
        return new ModelAndView("welcome", "message", message);
    }
}

