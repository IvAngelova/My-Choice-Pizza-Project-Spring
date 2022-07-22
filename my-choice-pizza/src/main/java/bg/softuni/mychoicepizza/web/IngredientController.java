package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.binding.IngredientAddBindingModel;
import bg.softuni.mychoicepizza.model.service.IngredientServiceModel;
import bg.softuni.mychoicepizza.service.IngredientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
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
            redirectAttributes.addFlashAttribute("ingredientAddBindingModel", ingredientAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ingredientAddBindingModel", bindingResult);

            return "redirect:add";
        }

        ingredientService.addIngredient(modelMapper.map(ingredientAddBindingModel, IngredientServiceModel.class));

        return "redirect:/admin/ingredients/update";
    }

    @ModelAttribute
    public IngredientAddBindingModel ingredientAddBindingModel() {
        return new IngredientAddBindingModel();
    }


    @Transactional
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        ingredientService.deleteIngredientById(id);

        return "redirect:/admin/ingredients/update";
    }

}
