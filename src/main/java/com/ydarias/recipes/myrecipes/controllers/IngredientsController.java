package com.ydarias.recipes.myrecipes.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ydarias.recipes.myrecipes.ingredients.IngredientsCatalog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientsController {

    private IngredientsCatalog ingredientsCatalog;

    public IngredientsController(IngredientsCatalog ingredientsCatalog) {
        this.ingredientsCatalog = ingredientsCatalog;
    }

    @GetMapping("/ingredients")
    public List<IngredientResponse> getIngredients() {
        var ingredients = ingredientsCatalog.getIngredients();
        return ingredients.stream().map(ingredient -> {
            var ingredientResponse = new IngredientResponse();
            ingredientResponse.setId(ingredient.getId());
            ingredientResponse.setName(ingredient.getName());
            ingredientResponse.setSeasonality(ingredient.getSeasonality());

            return ingredientResponse;
        }).collect(Collectors.toList());
    }
}
