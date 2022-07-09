package bg.softuni.mychoicepizza.model.entity;

import bg.softuni.mychoicepizza.model.entity.enums.PizzaBaseEnum;
import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class PizzaEntity extends BaseEntity{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SizeEnum size;

    @ManyToMany
    private List<IngredientEntity> ingredients;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PizzaBaseEnum base;

    @ManyToOne(optional = false)
    private PriceEntity price;

    public PizzaEntity() {
    }

    public SizeEnum getSize() {
        return size;
    }

    public PizzaEntity setSize(SizeEnum size) {
        this.size = size;
        return this;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public PizzaEntity setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public PizzaBaseEnum getBase() {
        return base;
    }

    public PizzaEntity setBase(PizzaBaseEnum base) {
        this.base = base;
        return this;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public PizzaEntity setPrice(PriceEntity price) {
        this.price = price;
        return this;
    }
}
