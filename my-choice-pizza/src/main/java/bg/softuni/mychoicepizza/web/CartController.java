package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.security.Principal;

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
        model.addAttribute("pizzas", cartService.findAllPizzasByUser(principal.getName()));
        return "shopping-cart";
    }
}
