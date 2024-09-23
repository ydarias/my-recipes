package com.ydarias.recipes.myrecipes.ingredients;

public class AlreadyExistingIngredientException extends RuntimeException {
    public AlreadyExistingIngredientException(String message) {
        super(message);
    }
}
