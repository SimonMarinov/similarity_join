package com.marinsim.similarity_join.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    void helloWorld(){
        System.out.println("Hello");
    }

}
