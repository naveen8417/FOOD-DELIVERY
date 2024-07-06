package com.naveenrestaurant.application.Controller;

import com.naveenrestaurant.application.Service.CartService;
import com.naveenrestaurant.application.Service.UserService;
import com.naveenrestaurant.application.model.Cart;
import com.naveenrestaurant.application.model.CartItem;
import com.naveenrestaurant.application.model.User;
import com.naveenrestaurant.application.request.CartItemRequest;
import com.naveenrestaurant.application.request.UpdateCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/cart/add")
    public ResponseEntity<CartItem> addItemtocart(@RequestBody CartItemRequest req, @RequestHeader("Authorization") String jwt) throws Exception{
        CartItem item = cartService.addItemToCart(req, jwt);
        return  new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateItemtocart(@RequestBody UpdateCartItem req, @RequestHeader("Authorization") String jwt) throws Exception{
        CartItem item = cartService.updatecartItemQuantity(req.getCartItemId(), req.getQuantity());
        return  new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @DeleteMapping("/cart-item/{id}/delete")
    public ResponseEntity<Cart> deleteItemtocart(@PathVariable long id, @RequestHeader("Authorization") String jwt) throws Exception{
        Cart cart =cartService.deletCartItem(id ,jwt);
        return  new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearItemtocart( @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart item = cartService.clearcart(user.getId());
        return  new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping("/cart/finduserr")
    public ResponseEntity<Cart> findusercart(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart item = cartService.findByUserId(user.getId());
        return  new ResponseEntity<>(item, HttpStatus.CREATED);
    }
}
