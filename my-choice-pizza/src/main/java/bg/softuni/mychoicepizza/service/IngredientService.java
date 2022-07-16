package bg.softuni.mychoicepizza.service;

import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;
import bg.softuni.mychoicepizza.model.service.IngredientServiceModel;
import bg.softuni.mychoicepizza.model.view.IngredientViewModel;

import java.io.IOException;
import java.util.List;

public interface IngredientService {
    void addIngredient(IngredientServiceModel ingredientServiceModel) throws IOException;

    List<IngredientViewModel> findAllIngredientsByCategory(CategoryNameEnum categoryNameEnum);

    void deleteIngredientById(Long id);
}
