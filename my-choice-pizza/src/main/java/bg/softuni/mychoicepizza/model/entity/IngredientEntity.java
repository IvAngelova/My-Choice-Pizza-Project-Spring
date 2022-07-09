package bg.softuni.mychoicepizza.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
public class IngredientEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;

    public IngredientEntity() {
    }

    public String getName() {
        return name;
    }

    public IngredientEntity setName(String name) {
        this.name = name;
        return this;
    }
}
