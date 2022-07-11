package bg.softuni.mychoicepizza.service;

import bg.softuni.mychoicepizza.model.service.IngredientServiceModel;

import java.io.IOException;

public interface IngredientService {
    void addIngredient(IngredientServiceModel ingredientServiceModel) throws IOException;
}
