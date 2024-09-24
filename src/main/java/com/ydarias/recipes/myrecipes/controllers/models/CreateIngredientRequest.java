package com.ydarias.recipes.myrecipes.controllers.models;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CreateIngredientRequest(
    @NotBlank(message =  "The name is mandatory") String name,
    @NotEmpty(message = "The seasonality cannot be an empty list") List<String> seasonality) {
}
