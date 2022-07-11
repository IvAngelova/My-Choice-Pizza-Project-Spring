package bg.softuni.mychoicepizza.service;

import bg.softuni.mychoicepizza.model.entity.CategoryEntity;
import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum);
}
