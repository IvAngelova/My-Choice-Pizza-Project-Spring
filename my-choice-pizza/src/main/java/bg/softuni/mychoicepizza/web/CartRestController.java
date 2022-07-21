package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.view.UserViewModel;
import bg.softuni.mychoicepizza.service.CartService;
import bg.softuni.mychoicepizza.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.math.BigDecimal;
import java.security.Principal;

@RestController
public class CartRestController {

    private final CartService cartService;
    private final UserService userService;

    public CartRestController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }


    @PostMapping("/cart/update/{iid}/{qty}")
    public String updateQuantity(@PathVariable("iid") Long itemId,
                                 @PathVariable("qty") Integer quantity,
                                 Principal principal) {

        if (principal == null) {
            return "you must login before updating cart";
        }

        UserViewModel userViewModel = userService.findUserByUsername(principal.getName());
        BigDecimal newSubTotal = cartService.updateQuantity(quantity, itemId, userViewModel.getId());

        return String.valueOf(newSubTotal);
    }

    @DeleteMapping("/cart/remove/{iid}")
    public String removeItemFromCart(@PathVariable("iid") Long itemId,
                                     Principal principal,
                                     RedirectAttributes redirectAttributes) {


        if (principal == null) {
            return "you must login before removing item";
        }

        UserViewModel userViewModel = userService.findUserByUsername(principal.getName());
        cartService.removeItem(itemId, userViewModel.getId());
//         redirectAttributes.addFlashAttribute("success", null);

        return "The item has been successfully removed from your cart.";
    }
}
