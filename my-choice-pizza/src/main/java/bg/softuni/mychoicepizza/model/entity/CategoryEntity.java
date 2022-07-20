package bg.softuni.mychoicepizza.model.entity;

import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;

    @OneToMany(mappedBy = "category", targetEntity = IngredientEntity.class, fetch = FetchType.EAGER)
    private List<IngredientEntity> ingredients;

    public CategoryEntity() {
    }

    public CategoryNameEnum getName() {
        return name;
    }

    public CategoryEntity setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public CategoryEntity setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
