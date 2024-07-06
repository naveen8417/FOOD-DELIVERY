package com.naveenrestaurant.application.Controller;

import com.naveenrestaurant.application.Response.MessageResponse;
import com.naveenrestaurant.application.Service.RestaurantService;
import com.naveenrestaurant.application.Service.UserService;
import com.naveenrestaurant.application.model.Restaurant;
import com.naveenrestaurant.application.model.User;
import com.naveenrestaurant.application.request.CreateRestaurantRequest;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/restaurant")
public class AdminRestaurantController {

    @Autowired
     private RestaurantService restaurantService;

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt) throws Exception {

        User user =userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.createRestaurant(req,user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest req, @RequestHeader("Authorization") String jwt,@PathVariable Long id) throws Exception {

        User user =userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurant(id,req);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(@RequestHeader("Authorization") String jwt,@PathVariable Long id) throws Exception {

        User user =userService.findUserByJwtToken(jwt);

        restaurantService.deleteRestaurant(id);
        MessageResponse msg = new MessageResponse();
        msg.setMessage("Delete sucessfully");

        return new ResponseEntity<>(msg, HttpStatus.CREATED);

    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Restaurant> updateRestaurantStatus( @RequestHeader("Authorization") String jwt,@PathVariable Long id) throws Exception {

        User user =userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId(@RequestHeader("Authorization") String jwt) throws Exception {

        User user =userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.findRestaurantByUserId(user.getId());


        return new ResponseEntity<>(restaurant, HttpStatus.OK);

    }







}
