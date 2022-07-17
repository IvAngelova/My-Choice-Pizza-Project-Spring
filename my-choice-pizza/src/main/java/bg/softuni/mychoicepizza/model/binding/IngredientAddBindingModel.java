package bg.softuni.mychoicepizza.model.binding;

import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class IngredientAddBindingModel {
    @Size(min = 2, max = 40, message = "Името трябва да бъде между 2 и 40 символа!")
    @NotBlank(message = "Полето е задължително!")
    private String name;

    @NotNull(message = "Полето е задължително!")
    private CategoryNameEnum category;

    @NotNull(message = "Полето е задължително!")
    private MultipartFile picture;

    public IngredientAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public IngredientAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public IngredientAddBindingModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public IngredientAddBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
