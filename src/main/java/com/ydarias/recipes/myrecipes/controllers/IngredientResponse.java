package com.ydarias.recipes.myrecipes.controllers;

import java.util.Arrays;
import java.util.Objects;

import com.ydarias.recipes.myrecipes.ingredients.Ingredient;

public class IngredientResponse {
    private String id;
    private String name;
    private String[] seasonality;

    public static IngredientResponse build(String id, String name, String[] seasonality) {
        var ingredient = new IngredientResponse();
        ingredient.setId(id);
        ingredient.setName(name);
        ingredient.setSeasonality(seasonality);

        return ingredient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSeasonality() {
        return seasonality;
    }

    public void setSeasonality(String[] seasonality) {
        this.seasonality = seasonality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IngredientResponse that = (IngredientResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.deepEquals(seasonality, that.seasonality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, Arrays.hashCode(seasonality));
    }
}
