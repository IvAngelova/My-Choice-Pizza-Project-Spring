package bg.softuni.mychoicepizza.model.view;

import bg.softuni.mychoicepizza.model.entity.enums.PizzaBaseEnum;
import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;

import java.math.BigDecimal;
import java.util.List;

public class PizzaViewModel {

    private SizeEnum size;

    private List<String> ingredients;

    private PizzaBaseEnum base;

    private BigDecimal price;

    private Integer quantity;

    public SizeEnum getSize() {
        return size;
    }

    public PizzaViewModel setSize(SizeEnum size) {
        this.size = size;
        return this;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public PizzaViewModel setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public PizzaBaseEnum getBase() {
        return base;
    }

    public PizzaViewModel setBase(PizzaBaseEnum base) {
        this.base = base;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PizzaViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public PizzaViewModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
