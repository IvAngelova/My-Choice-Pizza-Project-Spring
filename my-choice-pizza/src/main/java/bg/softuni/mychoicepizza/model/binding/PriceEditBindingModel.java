package bg.softuni.mychoicepizza.model.binding;

import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PriceEditBindingModel {
    private Long id;

    @Positive(message = "Цената трябва да бъде положително число!")
    @NotNull(message = "Полето е задължително!")
    private BigDecimal basePrice;

    @Positive(message = "Цената трябва да бъде положително число!")
    @NotNull(message = "Полето е задължително!")
    private BigDecimal additionalProductPrice;

    private SizeEnum pizzaSize;

    public PriceEditBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public PriceEditBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public SizeEnum getPizzaSize() {
        return pizzaSize;
    }

    public PriceEditBindingModel setPizzaSize(SizeEnum pizzaSize) {
        this.pizzaSize = pizzaSize;
        return this;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public PriceEditBindingModel setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public BigDecimal getAdditionalProductPrice() {
        return additionalProductPrice;
    }

    public PriceEditBindingModel setAdditionalProductPrice(BigDecimal additionalProductPrice) {
        this.additionalProductPrice = additionalProductPrice;
        return this;
    }
}
