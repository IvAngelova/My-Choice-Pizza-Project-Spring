package bg.softuni.mychoicepizza.service.impl;

import bg.softuni.mychoicepizza.exception.ObjectNotFoundException;
import bg.softuni.mychoicepizza.model.entity.*;
import bg.softuni.mychoicepizza.model.entity.enums.DeliveryEnum;
import bg.softuni.mychoicepizza.model.entity.enums.OrderStatusEnum;
import bg.softuni.mychoicepizza.model.view.OrderViewModel;
import bg.softuni.mychoicepizza.model.view.PizzaViewModel;
import bg.softuni.mychoicepizza.repository.CartItemRepository;
import bg.softuni.mychoicepizza.repository.OrderRepository;
import bg.softuni.mychoicepizza.repository.PizzaRepository;
import bg.softuni.mychoicepizza.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, PizzaRepository pizzaRepository, CartItemRepository cartItemRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
        this.cartItemRepository = cartItemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void makeOrder(List<Long> pizzaIds, DeliveryEnum delivery, String username) {

        List<CartItemEntity> orderedPizzasCart = cartItemRepository.findByIdInAndUser_Username(pizzaIds, username);

        if (orderedPizzasCart.isEmpty()) {
            return;
        }

        CartItemEntity cartItem = orderedPizzasCart.get(0);
        UserEntity user = cartItem.getUser();

        List<PizzaEntity> orderList = new ArrayList<>();
        for (CartItemEntity cartItemEntity : orderedPizzasCart) {
            PizzaEntity pizzaEntity = modelMapper.map(cartItemEntity, PizzaEntity.class);
            pizzaEntity.setId(null);
            orderList.add(pizzaEntity);
        }

        pizzaRepository.saveAll(orderList);
        cartItemRepository.deleteAllByIdInAndUser_Username(pizzaIds, username);

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (PizzaEntity pizza : orderList) {
            totalPrice = totalPrice.add(pizza.getSubtotal());
        }

        if (delivery.name().equals("С_ДОСТАВКА")) {
            totalPrice = totalPrice.add(new BigDecimal(1));
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUser(user)
                .setCreated(LocalDateTime.now())
                .setDelivery(delivery)
                .setPizzas(orderList)
                .setStatus(OrderStatusEnum.ЧАКАЩА)
                .setTotal(totalPrice);

        orderRepository.save(orderEntity);
    }

    @Override
    @Transactional
    public List<OrderViewModel> findAll() {
        return orderRepository.findAllByStatusOrderByCreatedAsc(OrderStatusEnum.ЧАКАЩА)
                .stream()
                .map(orderEntity -> {
                    OrderViewModel orderViewModel = modelMapper.map(orderEntity, OrderViewModel.class);
                    List<PizzaViewModel> pizzas = getPizzaViewModels(orderEntity);
                    orderViewModel.setPizzas(pizzas);
                    orderViewModel.setTotal(String.format("%.2f",orderEntity.getTotal()));
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy / HH:mm");
                    String formatOrderCreated = df.format(orderEntity.getCreated());
                    orderViewModel.setCreated(formatOrderCreated);
                    return orderViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void readyOrder(Long id) {
        OrderEntity orderEntity = orderRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Не съществува такава поръчка!"));

        orderEntity.setStatus(OrderStatusEnum.ГОТОВА);
        orderRepository.save(orderEntity);
    }

    private List<PizzaViewModel> getPizzaViewModels(OrderEntity orderEntity) {
        return orderEntity.getPizzas().stream()
                .map(pizzaEntity -> {
                    PizzaViewModel pizzaViewModel = modelMapper.map(pizzaEntity, PizzaViewModel.class);
                    List<String> ingredients = pizzaEntity.getIngredients()
                            .stream()
                            .map(IngredientEntity::getName)
                            .toList();
                    pizzaViewModel.setIngredients(ingredients);
                    return pizzaViewModel;
                }).collect(Collectors.toList());
    }
}
