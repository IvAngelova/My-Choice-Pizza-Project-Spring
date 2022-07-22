package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CategoryService categoryService;

    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/ingredients/update")
    public String updateIngredient(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "update-ingredient";
    }


}
