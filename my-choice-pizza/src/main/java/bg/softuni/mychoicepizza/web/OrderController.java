package bg.softuni.mychoicepizza.web;


import bg.softuni.mychoicepizza.model.binding.OrderAddBindingModel;
import bg.softuni.mychoicepizza.model.entity.OrderEntity;
import bg.softuni.mychoicepizza.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public String addOrder(@RequestParam("pizzaIds") List<Long> pizzaIds,
                           @Valid OrderAddBindingModel orderAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel", bindingResult);

            return "redirect:/cart";
        }

        boolean orderSuccessful = orderService.makeOrder(pizzaIds, orderAddBindingModel.getDelivery(), principal.getName());

        if (orderSuccessful) {
            redirectAttributes.addFlashAttribute("success", "Поръчката беше направена успешно!");
        } else {
            redirectAttributes.addFlashAttribute("success", "Възникна проблем по време на Вашата поръчката! Моля поръчайте отново!");
        }
        return "redirect:/";
    }

    @PostMapping("/{id}/ready")
    public String readyOrder(@PathVariable Long id) {
        orderService.readyOrder(id);
        return "redirect:/admin/orders";
    }


}
