package bg.softuni.mychoicepizza.web;

import bg.softuni.mychoicepizza.model.binding.UserProfileBindingModel;
import bg.softuni.mychoicepizza.model.binding.UserRegisterBindingModel;
import bg.softuni.mychoicepizza.model.service.UserProfileServiceModel;
import bg.softuni.mychoicepizza.model.service.UserServiceModel;
import bg.softuni.mychoicepizza.model.view.UserViewModel;
import bg.softuni.mychoicepizza.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("passwordMatch")) {
            model.addAttribute("passwordMatch", true);
        }
        return "register";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }


    @PostMapping("/register")
    public String registerConfirm(
            @Valid UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (!userRegisterBindingModel.getConfirmPassword().equals(userRegisterBindingModel.getPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("passwordMatch", false);

            return "redirect:register";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }
        UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

        userService.registerUser(userServiceModel);

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                      String username,
                              RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("bad_credentials", true);
        redirectAttributes.addFlashAttribute("username", username);

        return "redirect:login";
    }


    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        UserViewModel userViewModel = userService.findUserByUsername(username);
        UserProfileBindingModel userModel = modelMapper.map(userViewModel, UserProfileBindingModel.class);
        model.addAttribute("userProfileBindingModel", userModel);
        return "user-profile";
    }

    @PatchMapping("/profile")
    public String editUserProfile(@RequestParam("id") Long id,
                                  @Valid UserProfileBindingModel userProfileBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Principal principal) {
        UserViewModel userViewModel = userService.findUserByUsername(principal.getName());
        if (!Objects.equals(userViewModel.getId(), id)) {
            return "error/403";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userProfileBindingModel", userProfileBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userProfileBindingModel",
                            bindingResult);

            return "redirect:/users/profile/error";
        }

        UserProfileServiceModel userProfileServiceModel = modelMapper
                .map(userProfileBindingModel, UserProfileServiceModel.class);
        userProfileServiceModel.setId(id);
        userService.editProfile(userProfileServiceModel);

        redirectAttributes.addFlashAttribute("success", "Промените по профила бяха запазени успешно!");
        return "redirect:/";
    }

    @GetMapping("/profile/error")
    public String editProfileError() {
        return "user-profile";
    }


    @PatchMapping("/{id}/changeRoles")
    public String changeRole(@PathVariable Long id) {
        userService.changeRole(id);
        return "redirect:/admin/allUsers";
    }


}
