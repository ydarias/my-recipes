package com.ydarias.recipes.myrecipes.controllers;

import java.util.List;

import com.ydarias.recipes.myrecipes.controllers.models.RecipeResponse;
import com.ydarias.recipes.myrecipes.recipes.RecipesCatalog;
import com.ydarias.recipes.myrecipes.recipes.models.Recipe;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipesController {
    private RecipesCatalog recipesCatalog;

    public RecipesController(RecipesCatalog recipesCatalog) {
        this.recipesCatalog = recipesCatalog;
    }

    @GetMapping("/recipes")
    public List<RecipeResponse> getRecipes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        var recipes = recipesCatalog.getRecipes(page, size);
        return recipes.stream().map(RecipesController::asRecipeResponse).toList();
    }

    private static RecipeResponse asRecipeResponse(Recipe recipe) {
        return new RecipeResponse(recipe.id(), recipe.name(), recipe.recap());
    }
}
