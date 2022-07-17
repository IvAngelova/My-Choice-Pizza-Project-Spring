package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.binding.PriceEditBindingModel;
import bg.softuni.mychoicepizza.model.entity.enums.SizeEnum;
import bg.softuni.mychoicepizza.model.service.PriceServiceModel;
import bg.softuni.mychoicepizza.model.view.PriceViewModel;
import bg.softuni.mychoicepizza.service.PriceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/priceList")
public class PriceController {
    private final PriceService priceService;
    private final ModelMapper modelMapper;

    public PriceController(PriceService priceService, ModelMapper modelMapper) {
        this.priceService = priceService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public String priceList(Model model) {
        model.addAttribute("small", priceService.findPriceByPizzaSize(SizeEnum.МАЛКА));
        model.addAttribute("medium", priceService.findPriceByPizzaSize(SizeEnum.СРЕДНА));
        model.addAttribute("large", priceService.findPriceByPizzaSize(SizeEnum.ГОЛЯМА));
        model.addAttribute("party", priceService.findPriceByPizzaSize(SizeEnum.ПАРТИ));
        return "prices";
    }

    @GetMapping("/{id}/edit")
    public String editPrice(@PathVariable Long id, Model model) {
        PriceViewModel priceViewModel = priceService.findById(id);
        PriceEditBindingModel priceModel = modelMapper.map(priceViewModel, PriceEditBindingModel.class);

        model.addAttribute("priceEditBindingModel", priceModel);

        return "edit-price";
    }

    @PatchMapping("/{id}/edit")
    public String updatePrice(@PathVariable Long id,
                              @Valid PriceEditBindingModel priceEditBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            PriceViewModel priceViewModel = priceService.findById(id);
            priceEditBindingModel.setPizzaSize(priceViewModel.getPizzaSize());
            redirectAttributes.addFlashAttribute("priceEditBindingModel", priceEditBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.priceEditBindingModel",
                            bindingResult);

            return "redirect:/priceList/" + id + "/edit/errors";
        }

        PriceServiceModel priceServiceModel = modelMapper
                .map(priceEditBindingModel, PriceServiceModel.class);
        priceServiceModel.setId(id);
        priceService.editPrice(priceServiceModel);

        return "redirect:/priceList";
    }

    @GetMapping("/{id}/edit/errors")
    public String editPriceError(@PathVariable Long id) {
        return "edit-price";
    }


}
