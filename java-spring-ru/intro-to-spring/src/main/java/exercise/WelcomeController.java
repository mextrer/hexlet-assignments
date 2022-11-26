package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {
    @GetMapping
    public String blank() {
        return "Welcome to Spring";
    }

    @GetMapping(path = "/hello")
    public String hello(@RequestParam(required = false) String name) {
        String secondWord = name == null ? "World" : name;
        return "Hello, " + secondWord;
    }
}
// END
