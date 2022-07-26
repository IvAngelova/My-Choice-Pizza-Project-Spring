package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.view.UserViewModel;
import bg.softuni.mychoicepizza.service.CategoryService;
import bg.softuni.mychoicepizza.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CategoryService categoryService;
    private final UserService userService;

    public AdminController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/ingredients/update")
    public String updateIngredient(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "update-ingredient";
    }

    @GetMapping("/allUsers")
    public String allUsers(Model model) {
        List<UserViewModel> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "users-roles";
    }

    @GetMapping("/orders")
    public String getAllOrders() {
        return "all-orders";
    }
}
