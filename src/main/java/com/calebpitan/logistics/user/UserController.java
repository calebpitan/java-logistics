package com.calebpitan.logistics.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    @GetMapping()
    public String index() {
        return "Hello World! Greetings from Spring Boot!";
    }
}
