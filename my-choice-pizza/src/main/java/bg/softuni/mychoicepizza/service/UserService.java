package bg.softuni.mychoicepizza.service;

import bg.softuni.mychoicepizza.model.service.UserProfileServiceModel;
import bg.softuni.mychoicepizza.model.service.UserServiceModel;
import bg.softuni.mychoicepizza.model.view.UserViewModel;

import java.util.List;

public interface UserService {
    boolean isUsernameFree(String username);

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserViewModel findUserByUsername(String username);

    void editProfile(UserProfileServiceModel userProfileServiceModel);

    void initializeAdmin();

    List<UserViewModel> getAllUsers();

    void changeRole(Long id);
}
