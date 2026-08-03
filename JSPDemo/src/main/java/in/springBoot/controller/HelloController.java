package in.springBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    public HelloController(){
        System.out.println("Hello Controller Loaded");
    }
    @GetMapping("/")
    public String home(Model model) {
        System.out.println("GET /home called");
        model.addAttribute("message", "Home");
        return "index";
    }

    @PostMapping("/greet")
    public String greet(@RequestParam("name") String name,
                        Model model) {

        model.addAttribute("message", "Hello " + name);

        return "index";
    }
}
