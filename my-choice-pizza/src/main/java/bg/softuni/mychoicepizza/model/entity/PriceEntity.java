package bg.softuni.mychoicepizza.model.entity;

import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "prices")
public class PriceEntity extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private SizeEnum pizzaSize;

    @Column(nullable = false)
    private BigDecimal basePrice;

    @Column(nullable = false)
    private BigDecimal additionalProductPrice;

    public PriceEntity() {
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public PriceEntity setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public BigDecimal getAdditionalProductPrice() {
        return additionalProductPrice;
    }

    public PriceEntity setAdditionalProductPrice(BigDecimal additionalProductPrice) {
        this.additionalProductPrice = additionalProductPrice;
        return this;
    }

    public SizeEnum getPizzaSize() {
        return pizzaSize;
    }

    public PriceEntity setPizzaSize(SizeEnum pizzaSize) {
        this.pizzaSize = pizzaSize;
        return this;
    }
}
