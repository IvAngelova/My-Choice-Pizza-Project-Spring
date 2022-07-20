package bg.softuni.mychoicepizza.model.service;

import bg.softuni.mychoicepizza.model.entity.enums.PizzaBaseEnum;
import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;
import bg.softuni.mychoicepizza.model.view.IngredientViewModel;

import java.util.List;

public class PizzaServiceModel {

    private SizeEnum size;

    private PizzaBaseEnum base;

    private List<String> ingredients;

    public SizeEnum getSize() {
        return size;
    }

    public PizzaServiceModel setSize(SizeEnum size) {
        this.size = size;
        return this;
    }

    public PizzaBaseEnum getBase() {
        return base;
    }

    public PizzaServiceModel setBase(PizzaBaseEnum base) {
        this.base = base;
        return this;
    }


    public List<String> getIngredients() {
        return ingredients;
    }

    public PizzaServiceModel setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
