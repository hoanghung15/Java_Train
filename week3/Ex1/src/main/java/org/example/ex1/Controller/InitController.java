package org.example.ex1.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "TAG-HELLO")
public class InitController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}

