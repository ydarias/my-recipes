package com.ydarias.recipes.myrecipes.ingredients;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.ydarias.recipes.myrecipes.ingredients.errors.AlreadyExistingIngredientException;
import com.ydarias.recipes.myrecipes.ingredients.errors.ValidationException;
import com.ydarias.recipes.myrecipes.ingredients.models.Ingredient;
import com.ydarias.recipes.myrecipes.ingredients.models.IngredientCreationCommand;
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
        IngredientsCatalog.validateIngredient(newIngredient);

        if (ingredientsRepository.doesExist(newIngredient.name())) {
            var errorMessage = MessageFormat.format("Ingredient {0} already exists", newIngredient.name());
            throw new AlreadyExistingIngredientException(errorMessage);
        }

        return ingredientsRepository.addIngredient(newIngredient);
    }

    private static void validateIngredient(IngredientCreationCommand newIngredient) {
        var errors = new ArrayList<String>();

        if (newIngredient.name() == null || newIngredient.name().isBlank()) {
            errors.add("Ingredient name is missing");
        }

        if (newIngredient.seasonality() == null || newIngredient.seasonality().size() == 0) {
            errors.add("Seasonality is missing");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
