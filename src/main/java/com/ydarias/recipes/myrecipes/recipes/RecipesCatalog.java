package com.ydarias.recipes.myrecipes.recipes;

import java.util.List;

import com.ydarias.recipes.myrecipes.recipes.models.Recipe;
import org.springframework.stereotype.Service;

@Service
public class RecipesCatalog {
    private ForPersistingRecipes recipesRepository;

    public RecipesCatalog(ForPersistingRecipes recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public List<Recipe> getRecipes(int page, int size) {
        return this.recipesRepository.getRecipes(page, size);
    }
}
