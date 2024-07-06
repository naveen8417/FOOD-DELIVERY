package com.naveenrestaurant.application.Controller;

import com.naveenrestaurant.application.Service.UserService;
import com.naveenrestaurant.application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception{
        User user= service.findUserByJwtToken(jwt);
        return new ResponseEntity<>(user,HttpStatus.OK);


    }

}
