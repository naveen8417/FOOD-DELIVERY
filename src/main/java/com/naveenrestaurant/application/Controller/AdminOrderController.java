package com.naveenrestaurant.application.Controller;

import com.naveenrestaurant.application.Service.OrderService;
import com.naveenrestaurant.application.Service.UserService;
import com.naveenrestaurant.application.model.Order;
import com.naveenrestaurant.application.model.User;
import com.naveenrestaurant.application.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long id ,@RequestParam(required = false) String order_status,@RequestHeader("Authorization") String jwt) throws Exception{
        User user  = userService.findUserByJwtToken(jwt);
        List<Order> order =orderService.getRestaurantOrder(id,order_status);
        return  new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/order/restaurant/{orderid}/{orderstatus}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderid ,@RequestParam String order_status,@RequestHeader("Authorization") String jwt) throws Exception{
        User user  = userService.findUserByJwtToken(jwt);
        Order order =orderService.updateOrder(orderid,order_status);
        return  new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
