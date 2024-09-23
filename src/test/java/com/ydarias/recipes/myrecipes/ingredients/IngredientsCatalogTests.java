package com.ydarias.recipes.myrecipes.ingredients;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import com.ydarias.recipes.myrecipes.ingredients.errors.AlreadyExistingIngredientException;
import com.ydarias.recipes.myrecipes.ingredients.models.Ingredient;
import com.ydarias.recipes.myrecipes.ingredients.models.IngredientCreationCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IngredientsCatalogTests {
    @Mock
    private ForPersistingIngredients ingredientsRepository;

    private IngredientsCatalog catalog;

    @BeforeEach
    void setUp() {
        catalog = new IngredientsCatalog(ingredientsRepository);
    }

    @Test
    void theCatalogGetsAnIngredientsPageFromTheRepository() {
        var ingredients = List.of(
                new Ingredient("23f3423f-3c38-48ec-afd9-0aceea05aa4d", "Lemon", List.of("JAN", "FEB", "MAR", "APR", "MAY")),
                new Ingredient("6ec213a1-9e1d-4a73-ba5f-dfc621102af9", "Onion", List.of("APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT")),
                new Ingredient("17edc0d1-5525-42d9-8d75-84c94996cd84", "Watermelon", List.of("JUN", "JUL", "AUG")));

        when(ingredientsRepository.getIngredients(1, 10)).thenReturn(ingredients);

        var result = catalog.getIngredients(1, 10);

        assertThat(result).containsAll(ingredients);
    }

    @Test
    void theCatalogCreatesTheIngredientAtTheRepository() {
        var newPear = new IngredientCreationCommand("Pear", List.of("JUL", "AUG", "SEP", "OCT", "NOV"));
        var createdPear = new Ingredient("8840e6d9-b11d-424f-91da-28c991f911eb", "Pear", List.of("JUL", "AUG", "SEP", "OCT", "NOV"));

        when(ingredientsRepository.addIngredient(newPear)).thenReturn(createdPear);

        var result = catalog.addIngredient(newPear);

        assertThat(result).isEqualTo(createdPear);
    }


    @Test
    void errorIsThrownIfIngredientAlreadyExistsByName() {
        var newPear = new IngredientCreationCommand("Pear", List.of("JUL", "AUG", "SEP", "OCT", "NOV"));

        when(ingredientsRepository.doesExist("Pear")).thenReturn(true);

        assertThrows(AlreadyExistingIngredientException.class, () -> catalog.addIngredient(newPear));
    }
}
