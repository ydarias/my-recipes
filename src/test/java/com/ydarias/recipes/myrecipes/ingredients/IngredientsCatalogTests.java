package com.ydarias.recipes.myrecipes.ingredients;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class IngredientsCatalogTests {
    @Test
    public void theCatalogGetsAnIngredientsPageFromTheRepository() {
        var ingredientsRepository = mock(ForPersistingIngredients.class);
        var catalog = new IngredientsCatalog(ingredientsRepository);
        var ingredients = Arrays.asList(
                Ingredient.build("Lemon", "23f3423f-3c38-48ec-afd9-0aceea05aa4d", new String[] {"JAN", "FEB", "MAR", "APR", "MAY"}),
                Ingredient.build("Onion", "6ec213a1-9e1d-4a73-ba5f-dfc621102af9", new String[] {"APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT"}),
                Ingredient.build("Watermelon", "17edc0d1-5525-42d9-8d75-84c94996cd84", new String[] {"JUN", "JUL", "AUG"}));

        when(ingredientsRepository.getIngredients(1, 10)).thenReturn(ingredients);

        var result = catalog.getIngredients(1, 10);

        assertThat(result).containsAll(ingredients);
    }
}
