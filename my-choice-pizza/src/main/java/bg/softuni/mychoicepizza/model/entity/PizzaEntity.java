package bg.softuni.mychoicepizza.model.entity;

import bg.softuni.mychoicepizza.model.entity.enums.PizzaBaseEnum;
import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class PizzaEntity extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SizeEnum size;

    @ManyToMany
    private List<IngredientEntity> ingredients;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PizzaBaseEnum base;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(optional = false)
    private UserEntity user;

    public int getQuantity() {
        return quantity;
    }

    public PizzaEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public PizzaEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public PizzaEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
