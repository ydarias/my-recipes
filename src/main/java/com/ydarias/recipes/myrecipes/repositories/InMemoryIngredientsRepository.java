package com.ydarias.recipes.myrecipes.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ydarias.recipes.myrecipes.ingredients.ForPersistingIngredients;
import com.ydarias.recipes.myrecipes.ingredients.Ingredient;
import com.ydarias.recipes.myrecipes.ingredients.IngredientCreationCommand;
import org.springframework.stereotype.Service;

@Service
public class InMemoryIngredientsRepository implements ForPersistingIngredients {
    private List<Ingredient> internalIngredientsDictionary = new ArrayList<>();

    public InMemoryIngredientsRepository() {
        internalIngredientsDictionary.add(Ingredient.build("23f3423f-3c38-48ec-afd9-0aceea05aa4d", "Lemon", Arrays.asList("JAN", "FEB", "MAR", "APR", "MAY")));
        internalIngredientsDictionary.add(Ingredient.build("6ec213a1-9e1d-4a73-ba5f-dfc621102af9", "Onion", Arrays.asList("APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT")));
        internalIngredientsDictionary.add(Ingredient.build("17edc0d1-5525-42d9-8d75-84c94996cd84", "Watermelon", Arrays.asList("JUN", "JUL", "AUG")));
    }

    @Override
    public List<Ingredient> getIngredients(int page, int size) {
        return internalIngredientsDictionary.stream()
                .sorted(Comparator.comparing(Ingredient::getName))
                .skip((page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());

    }

    @Override
    public Ingredient addIngredient(IngredientCreationCommand ingredient) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
