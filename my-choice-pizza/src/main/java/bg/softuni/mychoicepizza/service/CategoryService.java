package bg.softuni.mychoicepizza.service;

import bg.softuni.mychoicepizza.model.entity.CategoryEntity;
import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;
import bg.softuni.mychoicepizza.model.view.CategoryViewModel;

import java.util.List;

public interface CategoryService {
    CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum);

    List<CategoryViewModel> getAllCategories();
}
