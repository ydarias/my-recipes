package com.ydarias.recipes.myrecipes.ingredients;

import java.util.Arrays;
import java.util.Objects;

public class Ingredient {
    private String id;
    private String name;
    private String[] seasonality;

    public static Ingredient build(String id, String name, String[] seasonality) {
        var ingredient = new Ingredient();
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
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.deepEquals(seasonality, that.seasonality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, Arrays.hashCode(seasonality));
    }
}
