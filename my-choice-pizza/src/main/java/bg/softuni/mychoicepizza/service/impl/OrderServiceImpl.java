package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.model.entity.OrderEntity;
import bg.softuni.mychoicepizza.model.entity.PizzaEntity;
import bg.softuni.mychoicepizza.model.entity.UserEntity;
import bg.softuni.mychoicepizza.model.entity.enums.DeliveryEnum;
import bg.softuni.mychoicepizza.model.entity.enums.OrderStatusEnum;
import bg.softuni.mychoicepizza.model.view.PizzaViewModel;
import bg.softuni.mychoicepizza.repository.OrderRepository;
import bg.softuni.mychoicepizza.repository.PizzaRepository;
import bg.softuni.mychoicepizza.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PizzaRepository pizzaRepository) {
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public void makeOrder(List<Long> pizzaIds, DeliveryEnum delivery, BigDecimal total) {

        List<PizzaEntity> orderedPizzas = pizzaRepository.findByIdIn(pizzaIds);
        //todo exception pizza with id doesnt exist
        if (orderedPizzas.isEmpty()){
            return;
        }

        PizzaEntity pizzaEntity = orderedPizzas.get(0);
        UserEntity user = pizzaEntity.getUser();

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (PizzaEntity pizza : orderedPizzas) {
            totalPrice = totalPrice.add(pizza.getSubtotal());
        }

        if(delivery.name().equals("С_ДОСТАВКА")){
            totalPrice = totalPrice.add(new BigDecimal(1));
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUser(user)
                .setCreated(LocalDateTime.now())
                .setDelivery(delivery)
                .setPizzas(orderedPizzas)
                .setStatus(OrderStatusEnum.ЧАКАЩА)
                .setTotal(totalPrice);

        orderRepository.save(orderEntity);
    }
}
