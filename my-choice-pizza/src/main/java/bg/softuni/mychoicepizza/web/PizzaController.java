package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.binding.PizzaBindingModel;
import bg.softuni.mychoicepizza.model.service.PizzaServiceModel;
import bg.softuni.mychoicepizza.service.CartService;
import bg.softuni.mychoicepizza.service.CategoryService;
import bg.softuni.mychoicepizza.service.IngredientService;
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
    private final IngredientService ingredientService;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final CartService cartService;

    public PizzaController(IngredientService ingredientService, ModelMapper modelMapper, CategoryService categoryService, CartService cartService) {
        this.ingredientService = ingredientService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.cartService = cartService;
    }

    @GetMapping("/builder")
    public String build(Model model) {

        model.addAttribute("categories", categoryService.getAllCategories());

//        model.addAttribute("vegetables", ingredientService.findAllIngredientsByCategory(CategoryNameEnum.ЗЕЛЕНЧУЦИ));
//        model.addAttribute("milky", ingredientService.findAllIngredientsByCategory(CategoryNameEnum.МЛЕЧНИ_И_ЯЙЦЕ));
//        model.addAttribute("meat", ingredientService.findAllIngredientsByCategory(CategoryNameEnum.КОЛБАСИ));

        return "pizza-builder";
    }

    @PostMapping("/builder")
    public String buildPizzaConfirm(@Valid PizzaBindingModel pizzaBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    Principal principal) {

        //todo validation
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("pizzaBindingModel", pizzaBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.pizzaBindingModel",
                            bindingResult);

            return "redirect:builder";
        }

        PizzaServiceModel pizzaServiceModel = modelMapper.map(pizzaBindingModel, PizzaServiceModel.class);
        cartService.saveNewCartItem(pizzaServiceModel, principal.getName());

        return "redirect:/cart";
    }

    @ModelAttribute
    PizzaBindingModel pizzaBindingModel() {
        return new PizzaBindingModel();
    }
}
