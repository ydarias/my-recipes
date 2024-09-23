package com.ydarias.recipes.myrecipes.ingredients;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import com.ydarias.recipes.myrecipes.controllers.IngredientsController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class IngredientsCatalogTests {
    @Mock
    private ForPersistingIngredients ingredientsRepository;

    private IngredientsCatalog catalog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        catalog = new IngredientsCatalog(ingredientsRepository);
    }

    @Test
    void theCatalogGetsAnIngredientsPageFromTheRepository() {
        var ingredients = Arrays.asList(
                Ingredient.build("23f3423f-3c38-48ec-afd9-0aceea05aa4d", "Lemon", Arrays.asList("JAN", "FEB", "MAR", "APR", "MAY")),
                Ingredient.build("6ec213a1-9e1d-4a73-ba5f-dfc621102af9", "Onion", Arrays.asList("APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT")),
                Ingredient.build("17edc0d1-5525-42d9-8d75-84c94996cd84", "Watermelon", Arrays.asList("JUN", "JUL", "AUG")));

        when(ingredientsRepository.getIngredients(1, 10)).thenReturn(ingredients);

        var result = catalog.getIngredients(1, 10);

        assertThat(result).containsAll(ingredients);
    }

    @Test
    void theCatalogCreatesTheIngredientAtTheRepository() {
        var newPear =  IngredientCreationCommand.build("Pear", Arrays.asList("JUL", "AUG", "SEP", "OCT", "NOV"));
        var createdPear = Ingredient.build("8840e6d9-b11d-424f-91da-28c991f911eb", "Pear", Arrays.asList("JUL", "AUG", "SEP", "OCT", "NOV"));

        when(ingredientsRepository.addIngredient(newPear)).thenReturn(createdPear);

        var result = catalog.addIngredient(newPear);

        assertThat(result).isEqualTo(createdPear);
    }
}
