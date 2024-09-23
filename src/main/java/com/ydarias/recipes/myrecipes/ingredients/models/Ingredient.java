package com.ydarias.recipes.myrecipes.ingredients.models;

import java.util.List;

public record Ingredient(String id, String name, List<String> seasonality) {
}
