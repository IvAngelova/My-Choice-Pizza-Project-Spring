package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.binding.OrderAddBindingModel;
import bg.softuni.mychoicepizza.model.view.PizzaViewModel;
import bg.softuni.mychoicepizza.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    @Transactional
    public String cart(Model model, Principal principal) {
        List<PizzaViewModel> allPizzasByUser = cartService.findAllPizzasByUser(principal.getName());
        model.addAttribute("pizzas", allPizzasByUser);
        BigDecimal estimatedTotal = BigDecimal.ZERO;
        for (PizzaViewModel pizzaViewModel : allPizzasByUser) {
            estimatedTotal = estimatedTotal.add(pizzaViewModel.getSubtotal());
        }
        model.addAttribute("estimatedTotal", estimatedTotal);
        model.addAttribute("estimatedEndPrice", estimatedTotal);
        return "shopping-cart";
    }

    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel(){
        return new OrderAddBindingModel();
    }
}
