package com.ydarias.recipes.myrecipes.controllers;

import java.util.List;

public record IngredientResponse(String id, String name, List<String> seasonality) {
}
