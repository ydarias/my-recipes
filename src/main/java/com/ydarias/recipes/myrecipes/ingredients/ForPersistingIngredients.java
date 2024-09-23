package com.ydarias.recipes.myrecipes.ingredients;

import java.util.List;

import com.ydarias.recipes.myrecipes.ingredients.models.Ingredient;
import com.ydarias.recipes.myrecipes.ingredients.models.IngredientCreationCommand;

public interface ForPersistingIngredients {
    List<Ingredient> getIngredients(int page, int size);
    Ingredient addIngredient(IngredientCreationCommand newIngredient);
    boolean doesExist(String ingredientName);
}
