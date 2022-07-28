package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.binding.PizzaBindingModel;
import bg.softuni.mychoicepizza.model.service.PizzaServiceModel;
import bg.softuni.mychoicepizza.service.CartService;
import bg.softuni.mychoicepizza.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/pizza")
public class PizzaController {
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final CartService cartService;

    public PizzaController(ModelMapper modelMapper, CategoryService categoryService, CartService cartService) {
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.cartService = cartService;
    }

    @GetMapping("/builder")
    public String build(Model model) {

        model.addAttribute("categories", categoryService.getAllCategories());
        return "pizza-builder";
    }

    @PostMapping("/builder")
    public String buildPizzaConfirm(@Valid PizzaBindingModel pizzaBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("pizzaBindingModel", pizzaBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.pizzaBindingModel",
                            bindingResult);

            return "redirect:builder";
        }

        PizzaServiceModel pizzaServiceModel = modelMapper.map(pizzaBindingModel, PizzaServiceModel.class);
        cartService.addNewCartItem(pizzaServiceModel, principal.getName());

        return "redirect:/cart";
    }

    @ModelAttribute
    PizzaBindingModel pizzaBindingModel() {
        return new PizzaBindingModel();
    }
}
