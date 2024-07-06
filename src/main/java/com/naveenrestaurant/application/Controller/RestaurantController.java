package com.naveenrestaurant.application.Controller;

import com.naveenrestaurant.application.DTO.RestaurantDTO;
import com.naveenrestaurant.application.Response.MessageResponse;
import com.naveenrestaurant.application.Service.RestaurantService;
import com.naveenrestaurant.application.Service.UserService;
import com.naveenrestaurant.application.model.Restaurant;
import com.naveenrestaurant.application.model.User;
import com.naveenrestaurant.application.request.CreateRestaurantRequest;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {


    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestHeader("Authorization") String jwt ,@RequestParam String keyword) throws Exception {

        User user =userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurant = restaurantService.searchAllRestaurant(keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllResataurant( @RequestHeader("Authorization") String jwt ) throws Exception {

        User user =userService.findUserByJwtToken(jwt);

        List<Restaurant> restaurant = restaurantService.getAllReastaurant();
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> searchRestaurant(@RequestHeader("Authorization") String jwt ,@PathVariable Long id) throws Exception {

        User user =userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("/{id}/addfavourites")
    public ResponseEntity<RestaurantDTO> addToFavourites( @RequestHeader("Authorization") String jwt ,@PathVariable Long id) throws Exception {

        User user =userService.findUserByJwtToken(jwt);

        RestaurantDTO restaurant = restaurantService.addToFavourites(id,user);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

}
