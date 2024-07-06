package com.naveenrestaurant.application.Controller;

import com.naveenrestaurant.application.Service.IngredientsCategory;
import com.naveenrestaurant.application.model.IngrediantsList;
import com.naveenrestaurant.application.model.Ingrediantscategory;
import com.naveenrestaurant.application.request.IngredientCategoryList;
import com.naveenrestaurant.application.request.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/admin/ingredients")
public class Ingrediamtcontroller {

    @Autowired
    private IngredientsCategory ingredientsCategory;

    @PostMapping("/category")
    public ResponseEntity<IngrediantsList> createIngrediantsList(@RequestBody Ingredients req
                                                                           ) throws Exception{
         IngrediantsList item= ingredientsCategory.createIngredientsItem(req.getRestaurantid(),req.getName(), req.getCategoryid());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<Ingrediantscategory> createIngrediantscategory(@RequestBody IngredientCategoryList req
    ) throws Exception{
        Ingrediantscategory item= ingredientsCategory.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngrediantsList> createIngrediantsList(@PathVariable Long id
    ) throws Exception{
        IngrediantsList item= ingredientsCategory.updatestock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngrediantsList>> getRestaurantIngredients(@PathVariable Long id
    ) throws Exception{
        List<IngrediantsList> item= ingredientsCategory.findIngredientsItem(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<Ingrediantscategory>> getRestaurantIngredientsCategory(@PathVariable Long id
    ) throws Exception{
        List<Ingrediantscategory> item= (List<Ingrediantscategory>) ingredientsCategory.findIngredientsCategoryByRestaurtantId(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }




}
