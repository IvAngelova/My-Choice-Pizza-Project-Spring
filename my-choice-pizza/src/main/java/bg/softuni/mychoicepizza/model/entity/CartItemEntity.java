package bg.softuni.mychoicepizza.model.entity;

import bg.softuni.mychoicepizza.model.entity.enums.PizzaBaseEnum;
import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart_items")
public class CartItemEntity extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SizeEnum size;

    @ManyToMany(fetch = FetchType.EAGER)
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

    @Transient
    public BigDecimal getSubtotal() {
        return this.price.multiply(new BigDecimal(this.quantity));
    }

    public CartItemEntity() {
        this.ingredients = new ArrayList<>();
    }

    public SizeEnum getSize() {
        return size;
    }

    public CartItemEntity setSize(SizeEnum size) {
        this.size = size;
        return this;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public CartItemEntity setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public PizzaBaseEnum getBase() {
        return base;
    }

    public CartItemEntity setBase(PizzaBaseEnum base) {
        this.base = base;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CartItemEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public CartItemEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public CartItemEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
