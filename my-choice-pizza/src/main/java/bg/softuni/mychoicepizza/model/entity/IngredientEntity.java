package bg.softuni.mychoicepizza.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class IngredientEntity extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private CategoryEntity category;

    @OneToOne(mappedBy = "ingredient", targetEntity = PictureEntity.class)
    private PictureEntity picture;

    public IngredientEntity() {
    }

    public String getName() {
        return name;
    }

    public IngredientEntity setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public IngredientEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public IngredientEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }
}
