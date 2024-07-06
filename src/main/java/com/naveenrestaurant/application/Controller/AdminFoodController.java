package com.naveenrestaurant.application.Controller;

import com.naveenrestaurant.application.Response.MessageResponse;
import com.naveenrestaurant.application.Service.FoodService;
import com.naveenrestaurant.application.Service.RestaurantService;
import com.naveenrestaurant.application.Service.UserService;
import com.naveenrestaurant.application.model.Food;
import com.naveenrestaurant.application.model.Restaurant;
import com.naveenrestaurant.application.model.User;
import com.naveenrestaurant.application.request.FoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    FoodService foodservice;
    @Autowired
    UserService userService;
    @Autowired
    RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody FoodRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantid());
        Food foods = foodservice.createFood(req,req.getCategory(),restaurant);
        return  new ResponseEntity<>(foods, HttpStatus.CREATED );

    }


    @DeleteMapping("{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodservice.deleteFood(id);
        MessageResponse msg = new MessageResponse();
        msg.setMessage("Food Deleted Sucessfully");
        return  new ResponseEntity<>(msg,HttpStatus.OK );

    }

    @PutMapping("{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodservice.updateFoodById(id);
        return  new ResponseEntity<>(food,HttpStatus.CREATED);

    }

}
