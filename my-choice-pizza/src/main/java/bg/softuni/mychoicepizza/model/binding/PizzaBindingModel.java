package bg.softuni.mychoicepizza.model.binding;

import bg.softuni.mychoicepizza.model.entity.enums.PizzaBaseEnum;
import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PizzaBindingModel {

    @NotNull(message = "Полето е задължително!")
    private SizeEnum size;
    @NotNull(message = "Полето е задължително!")
    private PizzaBaseEnum base;

    private List<String> ingredients;

    public PizzaBindingModel() {
        this.ingredients = new ArrayList<>();
    }

    public SizeEnum getSize() {
        return size;
    }

    public PizzaBindingModel setSize(SizeEnum size) {
        this.size = size;
        return this;
    }

    public PizzaBaseEnum getBase() {
        return base;
    }

    public PizzaBindingModel setBase(PizzaBaseEnum base) {
        this.base = base;
        return this;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public PizzaBindingModel setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
