package com.naveenrestaurant.application.Controller;

import com.naveenrestaurant.application.Service.CategoryService;
import com.naveenrestaurant.application.Service.UserService;
import com.naveenrestaurant.application.model.Category;
import com.naveenrestaurant.application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                    @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        Category createdcategory = categoryService.createCategory(category.getName(), user.getId());
        return  new ResponseEntity<>(createdcategory, HttpStatus.CREATED);

    }

    @GetMapping("/category/restaurant")
    public ResponseEntity<List<Category> > getCategoryByRestaurantId(
                                                        @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        List<Category> createdcategory = categoryService.findCategoryByRestaiurantId(user.getId());
        return  new ResponseEntity<>(createdcategory, HttpStatus.CREATED);

    }
}
