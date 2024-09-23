package com.ydarias.recipes.myrecipes.repositories;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ydarias.recipes.myrecipes.ingredients.ForPersistingIngredients;
import com.ydarias.recipes.myrecipes.ingredients.Ingredient;
import com.ydarias.recipes.myrecipes.ingredients.IngredientCreationCommand;
import org.springframework.stereotype.Service;

@Service
public class InMemoryIngredientsRepository implements ForPersistingIngredients {
    private List<Ingredient> internalIngredientsDictionary = new ArrayList<>();

    public InMemoryIngredientsRepository() {
        internalIngredientsDictionary.add(new Ingredient("23f3423f-3c38-48ec-afd9-0aceea05aa4d", "Lemon", List.of("JAN", "FEB", "MAR", "APR", "MAY")));
        internalIngredientsDictionary.add(new Ingredient("6ec213a1-9e1d-4a73-ba5f-dfc621102af9", "Onion", List.of("APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT")));
        internalIngredientsDictionary.add(new Ingredient("17edc0d1-5525-42d9-8d75-84c94996cd84", "Watermelon", List.of("JUN", "JUL", "AUG")));
    }

    @Override
    public List<Ingredient> getIngredients(int page, int size) {
        return internalIngredientsDictionary.stream()
                .sorted(Comparator.comparing(Ingredient::name))
                .skip((page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());

    }

    @Override
    public Ingredient addIngredient(IngredientCreationCommand newIngredient) {
        var ingredient = new Ingredient(UUID.randomUUID().toString(), newIngredient.name(), newIngredient.seasonality());
        internalIngredientsDictionary.add(ingredient);

        return ingredient;
    }

    @Override
    public boolean doesExist(String ingredientName) {
        var matchingIngredients = internalIngredientsDictionary.stream().filter(ingredient -> ingredient.name().equals(ingredientName)).toList();
        return matchingIngredients.size() > 0;
    }
}
