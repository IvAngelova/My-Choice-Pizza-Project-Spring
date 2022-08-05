package bg.softuni.mychoicepizza.service;

import bg.softuni.mychoicepizza.model.entity.OrderEntity;
import bg.softuni.mychoicepizza.model.entity.enums.DeliveryEnum;
import bg.softuni.mychoicepizza.model.view.OrderViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    boolean makeOrder(List<Long> pizzaIds, DeliveryEnum delivery, String username);

    List<OrderViewModel> findAll();

    void readyOrder(Long id);
}
