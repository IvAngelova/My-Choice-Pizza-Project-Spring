package bg.softuni.mychoicepizza.service;

import bg.softuni.mychoicepizza.model.service.PizzaServiceModel;
import bg.softuni.mychoicepizza.model.view.PizzaViewModel;

import java.util.List;

public interface CartService {
    void saveNewCartItem(PizzaServiceModel pizzaServiceModel, String username);

    List<PizzaViewModel> findAllPizzasByUser(String username);
}
