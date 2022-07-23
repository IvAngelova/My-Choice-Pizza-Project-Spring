package bg.softuni.mychoicepizza.scheduler;

import bg.softuni.mychoicepizza.model.entity.enums.OrderStatusEnum;
import bg.softuni.mychoicepizza.repository.OrderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Scheduler {

    private final OrderRepository orderRepository;

    public Scheduler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //   0 0 2 * * * - once a day at 2am
    //every 10 sek  - "*/10 * * * * *"
    @Transactional
    @Scheduled(cron = "0 0 2 * * *")
    public void deleteAllOldOrders() {
        orderRepository.deleteAllOldOrders(OrderStatusEnum.ГОТОВА);
        System.out.println("scheduler");
    }
}
