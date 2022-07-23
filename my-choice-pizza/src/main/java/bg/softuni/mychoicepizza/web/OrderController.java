package bg.softuni.mychoicepizza.web;


import bg.softuni.mychoicepizza.model.binding.OrderAddBindingModel;
import bg.softuni.mychoicepizza.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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
                           RedirectAttributes redirectAttributes) {

        //todo validation
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel", bindingResult);

            return "redirect:add";
        }

        orderService.makeOrder(pizzaIds, orderAddBindingModel.getDelivery(), orderAddBindingModel.getTotal());

        return "redirect:/";
    }


}
