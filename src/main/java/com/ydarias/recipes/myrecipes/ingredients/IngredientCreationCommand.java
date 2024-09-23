package com.ydarias.recipes.myrecipes.ingredients;

import java.util.List;

public class IngredientCreationCommand {
    private String name;
    private List<String> seasonality;

    public static IngredientCreationCommand build(String name, List<String> seasonality) {
        var ingredient = new IngredientCreationCommand();
        ingredient.setName(name);
        ingredient.setSeasonality(seasonality);

        return ingredient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSeasonality() {
        return seasonality;
    }

    public void setSeasonality(List<String> seasonality) {
        this.seasonality = seasonality;
    }
}
