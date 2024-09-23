package com.ydarias.recipes.myrecipes.ingredients;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class IngredientsCatalog {
    private ForPersistingIngredients ingredientsRepository;

    public IngredientsCatalog(ForPersistingIngredients ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public List<Ingredient> getIngredients(int page, int size) {
        return ingredientsRepository.getIngredients(page, size);
    }

    public Ingredient addIngredient(IngredientCreationCommand newIngredient) {
        if (ingredientsRepository.doesExist(newIngredient.name())) {
            throw new AlreadyExistingIngredientException();
        }

        return ingredientsRepository.addIngredient(newIngredient);
    }
}
