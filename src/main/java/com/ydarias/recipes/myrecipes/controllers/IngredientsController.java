package com.ydarias.recipes.myrecipes.controllers;

import java.util.List;

import com.ydarias.recipes.myrecipes.controllers.models.CreateIngredientRequest;
import com.ydarias.recipes.myrecipes.controllers.models.IngredientResponse;
import com.ydarias.recipes.myrecipes.ingredients.IngredientsCatalog;
import com.ydarias.recipes.myrecipes.ingredients.errors.AlreadyExistingIngredientException;
import com.ydarias.recipes.myrecipes.ingredients.models.Ingredient;
import com.ydarias.recipes.myrecipes.ingredients.models.IngredientCreationCommand;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientsController {

    private IngredientsCatalog ingredientsCatalog;

    public IngredientsController(IngredientsCatalog ingredientsCatalog) {
        this.ingredientsCatalog = ingredientsCatalog;
    }

    @GetMapping("/ingredients")
    public List<IngredientResponse> getIngredients(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        var ingredients = ingredientsCatalog.getIngredients(page, size);
        return ingredients.stream().map(IngredientsController::asIngredientResponse).toList();
    }

    @PostMapping("/ingredients")
    public IngredientResponse addIngredient(@Valid @RequestBody CreateIngredientRequest newIngredient) {
        var newIngredientCommand = new IngredientCreationCommand(newIngredient.name(), newIngredient.seasonality());
        var createdIngredient = ingredientsCatalog.addIngredient(newIngredientCommand);
        return IngredientsController.asIngredientResponse(createdIngredient);
    }

    @ExceptionHandler(AlreadyExistingIngredientException.class)
    public ResponseEntity<String> handleIngredientAlreadyExists(AlreadyExistingIngredientException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    private static IngredientResponse asIngredientResponse(Ingredient ingredient) {
        return new IngredientResponse(ingredient.id(), ingredient.name(), ingredient.seasonality());
    }
}
