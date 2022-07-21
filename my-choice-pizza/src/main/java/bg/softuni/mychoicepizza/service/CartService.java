package bg.softuni.mychoicepizza.service;

import bg.softuni.mychoicepizza.model.service.PizzaServiceModel;
import bg.softuni.mychoicepizza.model.view.PizzaViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    void addNewCartItem(PizzaServiceModel pizzaServiceModel, String username);

    List<PizzaViewModel> findAllPizzasByUser(String username);

    BigDecimal updateQuantity(Integer quantity, Long itemId, Long userId);

    void removeItem(Long itemId, Long userId);
}
