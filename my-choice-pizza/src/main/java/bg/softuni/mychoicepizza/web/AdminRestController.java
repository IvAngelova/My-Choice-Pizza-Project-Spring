package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.view.OrderViewModel;
import bg.softuni.mychoicepizza.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    private final OrderService orderService;

    public AdminRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/api/orders")
    public ResponseEntity<List<OrderViewModel>> findAll() {
        List<OrderViewModel> orderViewModels = orderService.findAll();
        return ResponseEntity
                .ok(orderViewModels);
    }

}
