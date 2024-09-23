package com.ydarias.recipes.myrecipes.ingredients;

import java.util.List;

public record IngredientCreationCommand(String name, List<String> seasonality) {
}
