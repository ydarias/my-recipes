package com.ydarias.recipes.myrecipes.ingredients;

import java.util.List;

public record Ingredient(String id, String name, List<String> seasonality) {
}
