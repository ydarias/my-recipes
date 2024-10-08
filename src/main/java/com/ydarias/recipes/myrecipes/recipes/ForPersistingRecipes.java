package com.ydarias.recipes.myrecipes.recipes;

import java.util.List;

import com.ydarias.recipes.myrecipes.recipes.models.Recipe;

public interface ForPersistingRecipes {
    List<Recipe> getRecipes(int page, int size);
}
