package bg.softuni.mychoicepizza.model.service;

import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;
import org.springframework.web.multipart.MultipartFile;

public class IngredientServiceModel {
    private Long id;

    private String name;

    private CategoryNameEnum category;

    private MultipartFile picture;

    public IngredientServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public IngredientServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public IngredientServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public IngredientServiceModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public IngredientServiceModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
