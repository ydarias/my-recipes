package com.ydarias.recipes.myrecipes.ingredients.errors;

public class AlreadyExistingIngredientException extends RuntimeException {
    public AlreadyExistingIngredientException(String message) {
        super(message);
    }
}
