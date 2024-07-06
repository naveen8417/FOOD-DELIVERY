package com.naveenrestaurant.application.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Welcome to Naveen Food", HttpStatus.OK);
    }

}
