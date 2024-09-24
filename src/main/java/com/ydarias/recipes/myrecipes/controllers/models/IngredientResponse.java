package com.ydarias.recipes.myrecipes.controllers.models;

import java.util.List;

public record IngredientResponse(String id, String name, List<String> seasonality) {
}
