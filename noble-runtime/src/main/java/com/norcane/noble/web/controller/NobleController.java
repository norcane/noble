package com.norcane.noble.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NobleController {

    @GetMapping("/")
    public String index() {
        return "Hello from noble controller!";
    }
}
