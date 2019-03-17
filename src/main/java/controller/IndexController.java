package controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author danielmartins
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class IndexController {
    
    @GetMapping
    public String sayHello() {
        return "Hello and Welcome to the Software Enginnering application.\n";
    }
}
