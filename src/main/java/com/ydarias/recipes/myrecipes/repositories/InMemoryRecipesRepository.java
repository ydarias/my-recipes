package com.ydarias.recipes.myrecipes.repositories;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ydarias.recipes.myrecipes.recipes.ForPersistingRecipes;
import com.ydarias.recipes.myrecipes.recipes.models.Recipe;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryRecipesRepository implements ForPersistingRecipes {
    private List<Recipe> internalRecipesDictionary = new ArrayList<>();

    public InMemoryRecipesRepository() {
        internalRecipesDictionary.add(new Recipe("227e1f9d-c1f1-43bc-936e-a3e1d54b1003", "Lemonade", "Refreshing beverage"));
        internalRecipesDictionary.add(new Recipe("dcd5a01b-1812-410b-80eb-3f21aa33b301", "Poutine", "Canadian mix of potatoes with sauces"));
        internalRecipesDictionary.add(new Recipe("809e53ae-bc23-4d6b-9991-0b4ada6bd589", "Beef Wellington", "Expensive meat wrapped in fancy sauce and puff pastry"));
    }

    @Override
    public List<Recipe> getRecipes(int page, int size) {
        return internalRecipesDictionary.stream()
                .sorted(Comparator.comparing(Recipe::name))
                .skip((page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());
    }
}
