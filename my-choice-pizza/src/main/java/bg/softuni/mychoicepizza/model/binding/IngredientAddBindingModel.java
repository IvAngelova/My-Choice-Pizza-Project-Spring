package bg.softuni.mychoicepizza.model.binding;

import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;
import org.springframework.web.multipart.MultipartFile;

public class IngredientAddBindingModel {
    private String name;

    private CategoryNameEnum category;

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
