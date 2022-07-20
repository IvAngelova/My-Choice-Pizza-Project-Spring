package bg.softuni.mychoicepizza.model.view;

import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;

import java.util.List;

public class CategoryViewModel {

    private Long id;

    private CategoryNameEnum name;

    private List<IngredientViewModel> ingredients;

    public Long getId() {
        return id;
    }

    public CategoryViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public CategoryViewModel setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }

    public List<IngredientViewModel> getIngredients() {
        return ingredients;
    }

    public CategoryViewModel setIngredients(List<IngredientViewModel> ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
