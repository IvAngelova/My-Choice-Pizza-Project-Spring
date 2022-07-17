package bg.softuni.mychoicepizza.model.service;

import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;

import java.math.BigDecimal;

public class PriceServiceModel {
    private Long id;

    private SizeEnum pizzaSize;

    private BigDecimal basePrice;

    private BigDecimal additionalProductPrice;

    public Long getId() {
        return id;
    }

    public PriceServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public SizeEnum getPizzaSize() {
        return pizzaSize;
    }

    public PriceServiceModel setPizzaSize(SizeEnum pizzaSize) {
        this.pizzaSize = pizzaSize;
        return this;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public PriceServiceModel setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public BigDecimal getAdditionalProductPrice() {
        return additionalProductPrice;
    }

    public PriceServiceModel setAdditionalProductPrice(BigDecimal additionalProductPrice) {
        this.additionalProductPrice = additionalProductPrice;
        return this;
    }
}
