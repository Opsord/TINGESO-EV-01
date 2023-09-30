package TopEducation.TopEducationApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping

public class IndexController {

    @RequestMapping("/home")
    public String homePage() {
        return "index";
    }

}
