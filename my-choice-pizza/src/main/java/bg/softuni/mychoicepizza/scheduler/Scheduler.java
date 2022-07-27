package bg.softuni.mychoicepizza.scheduler;

import bg.softuni.mychoicepizza.model.entity.enums.OrderStatusEnum;
import bg.softuni.mychoicepizza.repository.OrderRepository;
import bg.softuni.mychoicepizza.repository.PizzaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class Scheduler {

    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;

    public Scheduler(OrderRepository orderRepository, PizzaRepository pizzaRepository) {
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
    }

    //   0 0 2 * * * - once a day at 2am
    //every 10 sek  - "*/10 * * * * *"
    @Transactional
    @Scheduled(cron = "0 0 2 * * *")
    public void deleteAllOldOrders() {
        orderRepository.deleteAllOldOrders(OrderStatusEnum.ГОТОВА);
        List<Long> allPizzaIdsFromOldOrders = pizzaRepository.getAllPizzaIdsFromOldOrders();
        pizzaRepository.deleteAllByIdIn(allPizzaIdsFromOldOrders);
        System.out.println("scheduler");
    }
}
