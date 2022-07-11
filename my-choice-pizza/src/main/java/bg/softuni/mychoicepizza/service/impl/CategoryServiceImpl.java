package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.model.entity.CategoryEntity;
import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;
import bg.softuni.mychoicepizza.repository.CategoryRepository;
import bg.softuni.mychoicepizza.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum) {
        return categoryRepository.findByName(categoryNameEnum)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
