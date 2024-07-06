package com.naveenrestaurant.application.Controller;

import com.naveenrestaurant.application.Response.MessageResponse;
import com.naveenrestaurant.application.Service.FoodService;
import com.naveenrestaurant.application.Service.RestaurantService;
import com.naveenrestaurant.application.Service.UserService;
import com.naveenrestaurant.application.model.Food;
import com.naveenrestaurant.application.model.Restaurant;
import com.naveenrestaurant.application.model.User;
import com.naveenrestaurant.application.request.FoodRequest;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    FoodService foodservice;
    @Autowired
    UserService userService;
    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name, @RequestHeader("Authorizarion") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> food = foodservice.searchFood(name);
        return  new ResponseEntity<>(food, HttpStatus.CREATED );

    }

    @GetMapping("/Restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> searchFood(@PathVariable Long restaurantId,
                                                   @RequestParam boolean vegetarian,
                                                      @RequestParam boolean nonvegetarian ,
                                                        @RequestParam boolean isseasonal,
                                                           @RequestParam(required = false) String category,
                                                           @RequestHeader("Authorizarion") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> food = foodservice.getAllRestaurants(restaurantId,vegetarian,nonvegetarian,isseasonal,category);
        return  new ResponseEntity<>(food, HttpStatus.OK);

    }


}
