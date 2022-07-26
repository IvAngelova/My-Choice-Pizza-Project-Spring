package bg.softuni.mychoicepizza.model.view;

import bg.softuni.mychoicepizza.model.entity.enums.DeliveryEnum;


import java.time.LocalDateTime;
import java.util.List;

public class OrderViewModel {

    private Long id;
    private UserViewModel user;
    private List<PizzaViewModel> pizzas;
    private LocalDateTime created;
    private String total;
    private DeliveryEnum delivery;

    public UserViewModel getUser() {
        return user;
    }

    public OrderViewModel setUser(UserViewModel user) {
        this.user = user;
        return this;
    }

    public List<PizzaViewModel> getPizzas() {
        return pizzas;
    }

    public OrderViewModel setPizzas(List<PizzaViewModel> pizzas) {
        this.pizzas = pizzas;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public OrderViewModel setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public String getTotal() {
        return total;
    }

    public OrderViewModel setTotal(String total) {
        this.total = total;
        return this;
    }

    public DeliveryEnum getDelivery() {
        return delivery;
    }

    public OrderViewModel setDelivery(DeliveryEnum delivery) {
        this.delivery = delivery;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OrderViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
