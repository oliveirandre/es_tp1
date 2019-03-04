package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author danielmartins
 */
@RestController
@RequestMapping("/")
public class IndexController {
    
    @GetMapping
    public String sayHello() {
        return "Hello and Welcome to the Software Enginnering application.";
    }
}
