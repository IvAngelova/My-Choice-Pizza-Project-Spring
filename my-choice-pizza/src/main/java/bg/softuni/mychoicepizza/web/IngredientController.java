package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.binding.IngredientAddBindingModel;
import bg.softuni.mychoicepizza.model.service.IngredientServiceModel;
import bg.softuni.mychoicepizza.service.IngredientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    private final ModelMapper modelMapper;
    private final IngredientService ingredientService;

    public IngredientController(ModelMapper modelMapper, IngredientService ingredientService) {
        this.modelMapper = modelMapper;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/add")
    public String add() {
        return "add-ingredient";
    }



    @PostMapping("/add")
    public String addConfirm(@Valid IngredientAddBindingModel ingredientAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddBindingModel", ingredientAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ingredientAddBindingModel", bindingResult);

            return "redirect:add";
        }

        ingredientService.addIngredient(modelMapper.map(ingredientAddBindingModel, IngredientServiceModel.class));

        return "redirect:/";
    }

    @ModelAttribute
    public IngredientAddBindingModel ingredientAddBindingModel() {
        return new IngredientAddBindingModel();
    }


}
