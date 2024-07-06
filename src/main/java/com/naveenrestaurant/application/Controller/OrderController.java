package com.naveenrestaurant.application.Controller;

import com.naveenrestaurant.application.Service.OrderService;
import com.naveenrestaurant.application.Service.UserService;
import com.naveenrestaurant.application.model.CartItem;
import com.naveenrestaurant.application.model.Order;
import com.naveenrestaurant.application.model.User;
import com.naveenrestaurant.application.request.CartItemRequest;
import com.naveenrestaurant.application.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @PostMapping("/order")
    public ResponseEntity<Order> addItemtocart(@RequestBody OrderRequest req, @RequestHeader("Authorization") String jwt) throws Exception{
       User user  = userService.findUserByJwtToken(jwt);
       Order order =orderService.createOrder(req,user);
        return  new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/orderhistory")
    public ResponseEntity<List<Order>> getOrderHistory( @RequestHeader("Authorization") String jwt) throws Exception{
        User user  = userService.findUserByJwtToken(jwt);
        List<Order> order =orderService.getUsersOrder(user.getId());
        return  new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
