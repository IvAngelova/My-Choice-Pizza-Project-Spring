package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.model.entity.CategoryEntity;
import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;
import bg.softuni.mychoicepizza.model.view.CategoryViewModel;
import bg.softuni.mychoicepizza.repository.CategoryRepository;
import bg.softuni.mychoicepizza.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum) {
        return categoryRepository.findByName(categoryNameEnum)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public List<CategoryViewModel> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryEntity ->
                        modelMapper.map(categoryEntity, CategoryViewModel.class))
                .collect(Collectors.toList());
    }
}
