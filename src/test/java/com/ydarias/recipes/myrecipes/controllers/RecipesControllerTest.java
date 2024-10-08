package com.ydarias.recipes.myrecipes.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import com.ydarias.recipes.myrecipes.controllers.models.RecipeResponse;
import com.ydarias.recipes.myrecipes.recipes.RecipesCatalog;
import com.ydarias.recipes.myrecipes.recipes.models.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecipesControllerTest {

    @Mock
    private RecipesCatalog recipesCatalog;

    private RecipesController controller;

    @BeforeEach
    void setUp() {
        controller = new RecipesController(recipesCatalog);
    }

    @Test
    void recipesAreRecoveredFromRecipesCatalog() {
        var recipes = List.of(
                new Recipe("227e1f9d-c1f1-43bc-936e-a3e1d54b1003", "Lemonade", "Refreshing beverage"),
                new Recipe("dcd5a01b-1812-410b-80eb-3f21aa33b301", "Poutine", "Canadian mix of potatoes with sauces")
        );

        when(recipesCatalog.getRecipes(1, 10)).thenReturn(recipes);

        var result = controller.getRecipes(1, 10);

        var expectedRecipes = List.of(
                new RecipeResponse("227e1f9d-c1f1-43bc-936e-a3e1d54b1003", "Lemonade", "Refreshing beverage"),
                new RecipeResponse("dcd5a01b-1812-410b-80eb-3f21aa33b301", "Poutine", "Canadian mix of potatoes with sauces")
        );
        assertThat(result).containsAll(expectedRecipes);
    }
}
