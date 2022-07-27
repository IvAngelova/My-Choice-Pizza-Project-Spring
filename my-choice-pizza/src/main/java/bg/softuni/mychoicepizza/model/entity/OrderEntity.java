package bg.softuni.mychoicepizza.model.entity;

import bg.softuni.mychoicepizza.model.entity.enums.DeliveryEnum;
import bg.softuni.mychoicepizza.model.entity.enums.OrderStatusEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity{

    @ManyToOne(optional = false)
    private UserEntity user;

    @ManyToMany
    private List<PizzaEntity> pizzas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatusEnum status;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryEnum delivery;

    public OrderEntity() {
    }

    public UserEntity getUser() {
        return user;
    }

    public OrderEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public List<PizzaEntity> getPizzas() {
        return pizzas;
    }

    public OrderEntity setPizzas(List<PizzaEntity> pizzas) {
        this.pizzas = pizzas;
        return this;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public OrderEntity setStatus(OrderStatusEnum status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public OrderEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public OrderEntity setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public DeliveryEnum getDelivery() {
        return delivery;
    }

    public OrderEntity setDelivery(DeliveryEnum delivery) {
        this.delivery = delivery;
        return this;
    }
}
