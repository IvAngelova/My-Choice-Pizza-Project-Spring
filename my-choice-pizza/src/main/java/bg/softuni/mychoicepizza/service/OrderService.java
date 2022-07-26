package bg.softuni.mychoicepizza.service;

import bg.softuni.mychoicepizza.model.entity.enums.DeliveryEnum;
import bg.softuni.mychoicepizza.model.view.OrderViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void makeOrder(List<Long> pizzaIds, DeliveryEnum delivery, BigDecimal total, String username);

    List<OrderViewModel> findAll();
}
