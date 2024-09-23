package com.ydarias.recipes.myrecipes.ingredients;

import java.util.List;

public interface ForPersistingIngredients {
    List<Ingredient> getIngredients(int page, int size);
    Ingredient addIngredient(IngredientCreationCommand newIngredient);
}
