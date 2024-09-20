package com.ydarias.recipes.myrecipes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientsController {

    @GetMapping("/ingredients")
    public String getIngredients() {
        return "This is the list of ingredients";
    }
}
