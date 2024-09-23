package com.ydarias.recipes.myrecipes.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import com.ydarias.recipes.myrecipes.ingredients.Ingredient;
import com.ydarias.recipes.myrecipes.ingredients.IngredientCreationCommand;
import com.ydarias.recipes.myrecipes.ingredients.IngredientsCatalog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IngredientsControllerTests {
    @Mock
    private IngredientsCatalog ingredientsCatalog;

    private IngredientsController controller;

    @BeforeEach
    void setUp() {
        controller = new IngredientsController(ingredientsCatalog);
    }

    @Test
    void ingredientsAreRecoveredFromIngredientsCatalog() {
        var ingredients = List.of(
                new Ingredient("23f3423f-3c38-48ec-afd9-0aceea05aa4d", "Lemon", List.of("JAN", "FEB", "MAR", "APR", "MAY")),
                new Ingredient("6ec213a1-9e1d-4a73-ba5f-dfc621102af9", "Onion", List.of("APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT")),
                new Ingredient("17edc0d1-5525-42d9-8d75-84c94996cd84", "Watermelon", List.of("JUN", "JUL", "AUG")));

        when(ingredientsCatalog.getIngredients(1, 10)).thenReturn(ingredients);

        var result = controller.getIngredients(1, 10);

        var expectedIngredients = List.of(
                new IngredientResponse("23f3423f-3c38-48ec-afd9-0aceea05aa4d", "Lemon", List.of("JAN", "FEB", "MAR", "APR", "MAY")),
                new IngredientResponse("6ec213a1-9e1d-4a73-ba5f-dfc621102af9", "Onion", List.of("APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT")),
                new IngredientResponse("17edc0d1-5525-42d9-8d75-84c94996cd84", "Watermelon", List.of("JUN", "JUL", "AUG")));
        assertThat(result).containsAll(expectedIngredients);
    }

    @Test
    void ingredientsAreAddedUsingIngredientsCatalog() {
        var newPear = new IngredientCreationCommand("Pear", List.of("JUL", "AUG", "SEP", "OCT", "NOV"));
        var createdPear = new Ingredient("8840e6d9-b11d-424f-91da-28c991f911eb", "Pear", List.of("JUL", "AUG", "SEP", "OCT", "NOV"));

        when(ingredientsCatalog.addIngredient(newPear)).thenReturn(createdPear);

        var result = controller.addIngredient(newPear);

        var expectedPear = new IngredientResponse("8840e6d9-b11d-424f-91da-28c991f911eb", "Pear", List.of("JUL", "AUG", "SEP", "OCT", "NOV"));
        assertThat(result).isEqualTo(expectedPear);
    }
}
