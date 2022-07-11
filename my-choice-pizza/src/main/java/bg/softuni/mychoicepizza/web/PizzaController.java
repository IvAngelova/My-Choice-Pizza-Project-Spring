package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.entity.enums.CategoryNameEnum;
import bg.softuni.mychoicepizza.service.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizza")
public class PizzaController {
    private final IngredientService ingredientService;

    public PizzaController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/builder")
    public String build(Model model) {

        model.addAttribute("vegetables", ingredientService.findAllIngredientsByCategory(CategoryNameEnum.ЗЕЛЕНЧУЦИ));
        model.addAttribute("milky", ingredientService.findAllIngredientsByCategory(CategoryNameEnum.МЛЕЧНИ_И_ЯЙЦЕ));
        model.addAttribute("meat", ingredientService.findAllIngredientsByCategory(CategoryNameEnum.КОЛБАСИ));

        return "pizza-builder";
    }

//    @PostMapping("/builder")
//    public String buildConfirm(){
//
//    }
}
