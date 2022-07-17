package bg.softuni.mychoicepizza.model.view;

import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;

import java.math.BigDecimal;

public class PriceViewModel {
    private Long id;

    private SizeEnum pizzaSize;

    private BigDecimal basePrice;

    private BigDecimal additionalProductPrice;

    public PriceViewModel() {
    }

    public Long getId() {
        return id;
    }

    public PriceViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public SizeEnum getPizzaSize() {
        return pizzaSize;
    }

    public PriceViewModel setPizzaSize(SizeEnum pizzaSize) {
        this.pizzaSize = pizzaSize;
        return this;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public PriceViewModel setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public BigDecimal getAdditionalProductPrice() {
        return additionalProductPrice;
    }

    public PriceViewModel setAdditionalProductPrice(BigDecimal additionalProductPrice) {
        this.additionalProductPrice = additionalProductPrice;
        return this;
    }
}
