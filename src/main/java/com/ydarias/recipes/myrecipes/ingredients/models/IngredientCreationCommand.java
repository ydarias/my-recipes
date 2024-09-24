package com.ydarias.recipes.myrecipes.ingredients.models;

import java.util.List;

public record IngredientCreationCommand(String name, List<String> seasonality) {
}
