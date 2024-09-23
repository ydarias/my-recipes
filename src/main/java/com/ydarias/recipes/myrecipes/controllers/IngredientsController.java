package com.ydarias.recipes.myrecipes.controllers;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ydarias.recipes.myrecipes.ingredients.Ingredient;
import com.ydarias.recipes.myrecipes.ingredients.IngredientsCatalog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientsController {

    private IngredientsCatalog ingredientsCatalog;

    public IngredientsController(IngredientsCatalog ingredientsCatalog) {
        this.ingredientsCatalog = ingredientsCatalog;
    }

    @GetMapping("/ingredients")
    public List<IngredientResponse> getIngredients(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        var ingredients = ingredientsCatalog.getIngredients(page, size);
        return ingredients.stream().map(asIngredientResponse()).collect(Collectors.toList());
    }

    private static Function<Ingredient, IngredientResponse> asIngredientResponse() {
        return ingredient -> {
            var ingredientResponse = new IngredientResponse();
            ingredientResponse.setId(ingredient.getId());
            ingredientResponse.setName(ingredient.getName());
            ingredientResponse.setSeasonality(ingredient.getSeasonality());

            return ingredientResponse;
        };
    }
}
